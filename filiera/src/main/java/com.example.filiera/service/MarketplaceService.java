package com.example.filiera.service;

import com.example.filiera.domain.Marketplace;
import com.example.filiera.domain.Offerta;
import com.example.filiera.domain.Pacchetto;
import com.example.filiera.domain.Prodotto;
import com.example.filiera.domain.StatoProdotto;
import com.example.filiera.domain.TipoOfferta;
import com.example.filiera.domain.Utente;
import com.example.filiera.dto.CreateListingRequest;
import com.example.filiera.dto.CreateMarketplaceRequest;
import com.example.filiera.factory.MarketplaceFactory;
import com.example.filiera.factory.OffertaFactory;
import com.example.filiera.repository.MarketplaceRepository;
import com.example.filiera.repository.OffertaRepository;
import com.example.filiera.repository.PacchettoRepository;
import com.example.filiera.repository.ProductRepository;
import com.example.filiera.repository.UserRepository;
import com.example.filiera.security.Action;
import com.example.filiera.security.PermissionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MarketplaceService {

    private final MarketplaceRepository marketplaceRepo;
    private final OffertaRepository offertaRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final PacchettoRepository pacchettoRepo;
    private final PermissionService permissionService = new PermissionService();
    private final MarketplaceFactory marketplaceFactory = new MarketplaceFactory();
    private final OffertaFactory offertaFactory = new OffertaFactory();

    public MarketplaceService(MarketplaceRepository marketplaceRepo,
                              OffertaRepository offertaRepo,
                              UserRepository userRepo,
                              ProductRepository productRepo,
                              PacchettoRepository pacchettoRepo) {
        this.marketplaceRepo = marketplaceRepo;
        this.offertaRepo = offertaRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.pacchettoRepo = pacchettoRepo;
    }

    @Transactional
    public Marketplace createMarketplace(CreateMarketplaceRequest req) {
        Utente u = userRepo.findById(req.getCreatoDaUserId()).orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));
        permissionService.strategyFor(Action.CREATE_MARKETPLACE).check(u);
        Marketplace m = marketplaceFactory.create(req);
        return marketplaceRepo.save(m);
    }

    @Transactional
    public Offerta addListing(CreateListingRequest req) {
        Marketplace m = marketplaceRepo.findById(req.getMarketplaceId()).orElseThrow(() -> new EntityNotFoundException("Marketplace non trovato"));
        Utente u = userRepo.findById(m.getCreatoDaUserId()).orElseThrow(() -> new EntityNotFoundException("Creatore marketplace non trovato"));
        permissionService.strategyFor(Action.ADD_LISTING).check(u);

        if (req.getTipo() == TipoOfferta.PRODOTTO) {
            Prodotto p = productRepo.findById(req.getProdottoId()).orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato"));
            if (p.getStato() != StatoProdotto.APPROVATO) throw new IllegalStateException("Prodotto non approvato");
            Offerta o = offertaFactory.create(req, m, p, null);
            return offertaRepo.save(o);
        } else {
            Pacchetto pac = pacchettoRepo.findById(req.getPacchettoId()).orElseThrow(() -> new EntityNotFoundException("Pacchetto non trovato"));
            Offerta o = offertaFactory.create(req, m, null, pac);
            return offertaRepo.save(o);
        }
    }
}
