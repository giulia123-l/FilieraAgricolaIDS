package com.example.filiera.service;

import com.example.filiera.domain.Prodotto;
import com.example.filiera.domain.Ruolo;
import com.example.filiera.domain.StatoProdotto;
import com.example.filiera.domain.StatoUtente;
import com.example.filiera.domain.TipoProdotto;
import com.example.filiera.domain.Utente;
import com.example.filiera.dto.CreateProductRequest;
import com.example.filiera.dto.ProductResponse;
import com.example.filiera.repository.ProductRepository;
import com.example.filiera.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;
    private final UserRepository userRepo;

    public ProductService(ProductRepository repo, UserRepository userRepo){
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Transactional
    public Prodotto create(CreateProductRequest req) {
        Utente u = userRepo.findById(req.getCreatoDaUserId())
                .orElseThrow(() -> new EntityNotFoundException("Utente " + req.getCreatoDaUserId() + " non trovato"));
        if (u.getStato() != StatoUtente.ATTIVO || u.getRuoloAssegnato() != Ruolo.PRODUTTORE) {
            throw new IllegalStateException("Solo un PRODUTTORE ATTIVO può creare prodotti base");
        }

        Prodotto p = new Prodotto();
        p.setNome(req.getNome());
        p.setDescrizione(req.getDescrizione());
        p.setLat(req.getLat());
        p.setLon(req.getLon());
        p.setCreatoDaUserId(req.getCreatoDaUserId());
        p.setStato(StatoProdotto.IN_ATTESA_APPROVAZIONE);
        p.setTipo(null);
        p.setNoteRifiuto(null);
        return repo.save(p);
    }

    public List<Prodotto> listPending() { return repo.findByStato(StatoProdotto.IN_ATTESA_APPROVAZIONE); }
    public List<Prodotto> listApproved() { return repo.findByStato(StatoProdotto.APPROVATO); }

    @Transactional
    public Prodotto approveById(Long productId, Long curatoreId) {
        Utente curatore = userRepo.findById(curatoreId)
                .orElseThrow(() -> new EntityNotFoundException("Curatore non trovato"));

        if (curatore.getStato() != StatoUtente.ATTIVO || curatore.getRuoloAssegnato() != Ruolo.CURATORE) {
            throw new IllegalStateException("Solo un CURATORE ATTIVO può approvare prodotti");
        }

        Prodotto p = repo.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Prodotto " + productId + " non trovato"));
        if (p.getStato() != StatoProdotto.IN_ATTESA_APPROVAZIONE) {
            throw new IllegalStateException("Prodotto non approvabile");
        }

        p.setStato(StatoProdotto.APPROVATO);
        p.setTipo(TipoProdotto.BASE);
        p.setNoteRifiuto(null);
        return repo.saveAndFlush(p);
    }

    @Transactional
    public Prodotto rejectById(Long productId, Long curatoreId, String motivazione) {
        Utente curatore = userRepo.findById(curatoreId)
                .orElseThrow(() -> new EntityNotFoundException("Curatore non trovato"));

        if (curatore.getStato() != StatoUtente.ATTIVO || curatore.getRuoloAssegnato() != Ruolo.CURATORE) {
            throw new IllegalStateException("Solo un CURATORE ATTIVO può rifiutare prodotti");
        }

        Prodotto p = repo.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Prodotto " + productId + " non trovato"));
        if (p.getStato() != StatoProdotto.IN_ATTESA_APPROVAZIONE) {
            throw new IllegalStateException("Prodotto non rifiutabile");
        }

        p.setStato(StatoProdotto.RIFIUTATO);
        p.setTipo(null);
        p.setNoteRifiuto(motivazione);
        return repo.saveAndFlush(p);
    }


    public static ProductResponse toResp(Prodotto p){
        ProductResponse r = new ProductResponse();
        r.setId(p.getId());
        r.setNome(p.getNome());
        r.setDescrizione(p.getDescrizione());
        r.setLat(p.getLat());
        r.setLon(p.getLon());
        r.setStato(p.getStato() != null ? p.getStato().name() : null);
        r.setCreatoDaUserId(p.getCreatoDaUserId());
        return r;
    }
}
