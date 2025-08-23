package com.example.filiera.service;

import com.example.filiera.domain.Pacchetto;
import com.example.filiera.domain.Prodotto;
import com.example.filiera.domain.Ruolo;
import com.example.filiera.domain.StatoProdotto;
import com.example.filiera.domain.StatoUtente;
import com.example.filiera.domain.Utente;
import com.example.filiera.dto.CreatePackageRequest;
import com.example.filiera.repository.PacchettoRepository;
import com.example.filiera.repository.ProductRepository;
import com.example.filiera.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacchettoService {

    private final PacchettoRepository repo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public PacchettoService(PacchettoRepository repo, ProductRepository productRepo, UserRepository userRepo) {
        this.repo = repo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public Pacchetto create(CreatePackageRequest req) {
        Utente u = userRepo.findById(req.getCreatoDaUserId())
                .orElseThrow(() -> new EntityNotFoundException("Utente " + req.getCreatoDaUserId() + " non trovato"));

        if (u.getStato() != StatoUtente.ATTIVO || u.getRuoloAssegnato() != Ruolo.DISTRIBUTORE) {
            throw new IllegalStateException("Solo un DISTRIBUTORE ATTIVO può creare pacchetti");
        }

        List<Prodotto> prodotti = productRepo.findAllById(req.getProductIds());
        if (prodotti.size() != req.getProductIds().size()) {
            throw new EntityNotFoundException("Alcuni prodotti non esistono");
        }
        for (Prodotto p : prodotti) {
            if (p.getStato() != StatoProdotto.APPROVATO) {
                throw new IllegalStateException("Prodotto " + p.getId() + " non è APPROVATO");
            }
        }

        Pacchetto pacchetto = new Pacchetto();
        pacchetto.setNome(req.getNome());
        pacchetto.setProdotti(prodotti);
        pacchetto.setCreatoDaUserId(req.getCreatoDaUserId());
        return repo.save(pacchetto);
    }
}
