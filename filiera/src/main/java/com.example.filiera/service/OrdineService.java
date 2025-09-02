package com.example.filiera.service;

import com.example.filiera.domain.Marketplace;
import com.example.filiera.domain.Offerta;
import com.example.filiera.domain.Ordine;
import com.example.filiera.domain.OrdineItem;
import com.example.filiera.domain.StatoOrdine;
import com.example.filiera.domain.Utente;
import com.example.filiera.dto.CreateOrderRequest;
import com.example.filiera.dto.OrderResponse;
import com.example.filiera.repository.MarketplaceRepository;
import com.example.filiera.repository.OffertaRepository;
import com.example.filiera.repository.OrdineRepository;
import com.example.filiera.repository.UserRepository;
import com.example.filiera.security.Action;
import com.example.filiera.security.PermissionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class OrdineService {

    private final OrdineRepository ordineRepo;
    private final OffertaRepository offertaRepo;
    private final MarketplaceRepository marketplaceRepo;
    private final UserRepository userRepo;
    private final PermissionService permissionService = new PermissionService();

    public OrdineService(OrdineRepository ordineRepo, OffertaRepository offertaRepo, MarketplaceRepository marketplaceRepo, UserRepository userRepo) {
        this.ordineRepo = ordineRepo;
        this.offertaRepo = offertaRepo;
        this.marketplaceRepo = marketplaceRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest req) {
        Utente buyer = userRepo.findById(req.getAcquirenteId()).orElseThrow(() -> new EntityNotFoundException("Acquirente non trovato"));
        permissionService.strategyFor(Action.CREATE_ORDER).check(buyer);
        Marketplace m = marketplaceRepo.findById(req.getMarketplaceId()).orElseThrow(() -> new EntityNotFoundException("Marketplace non trovato"));

        Ordine ordine = new Ordine();
        ordine.setMarketplace(m);
        ordine.setAcquirenteId(req.getAcquirenteId());
        ordine.setStato(StatoOrdine.CREATO);
        ordine.setItems(new ArrayList<>());

        BigDecimal totale = BigDecimal.ZERO;

        for (CreateOrderRequest.Item it : req.getItems()) {
            Offerta off = offertaRepo.findById(it.getListingId()).orElseThrow(() -> new EntityNotFoundException("Offerta non trovata"));
            if (!off.getMarketplace().getId().equals(m.getId())) throw new IllegalStateException("Offerta non appartiene al marketplace");
            if (off.getStock() < it.getQuantita()) throw new IllegalStateException("Stock insufficiente");

            off.setStock(off.getStock() - it.getQuantita());
            offertaRepo.save(off);

            OrdineItem row = new OrdineItem();
            row.setOrdine(ordine);
            row.setOfferta(off);
            row.setQuantita(it.getQuantita());
            row.setPrezzoUnitario(off.getPrezzo());
            row.setTotaleRiga(off.getPrezzo().multiply(BigDecimal.valueOf(it.getQuantita())));
            ordine.getItems().add(row);

            totale = totale.add(row.getTotaleRiga());
        }

        ordine.setTotale(totale);
        ordine.setStato(StatoOrdine.COMPLETATO);
        Ordine saved = ordineRepo.save(ordine);

        OrderResponse resp = new OrderResponse();
        resp.setId(saved.getId());
        resp.setStato(saved.getStato().name());
        resp.setTotale(saved.getTotale());
        var rows = new ArrayList<OrderResponse.Row>();
        for (OrdineItem r : saved.getItems()) {
            OrderResponse.Row rr = new OrderResponse.Row();
            rr.setListingId(r.getOfferta().getId());
            rr.setQuantita(r.getQuantita());
            rr.setPrezzoUnitario(r.getPrezzoUnitario());
            rr.setTotaleRiga(r.getTotaleRiga());
            rows.add(rr);
        }
        resp.setRighe(rows);
        return resp;
    }
}
