package com.example.filiera.service;

import com.example.filiera.domain.Prodotto;
import com.example.filiera.domain.Ruolo;
import com.example.filiera.domain.StatoProdotto;
import com.example.filiera.domain.StatoUtente;
import com.example.filiera.domain.TipoProdotto;
import com.example.filiera.domain.Trasformazione;
import com.example.filiera.domain.Utente;
import com.example.filiera.dto.CreateTransformedProductRequest;
import com.example.filiera.repository.ProductRepository;
import com.example.filiera.repository.TrasformazioneRepository;
import com.example.filiera.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrasformazioneService {

    private final TrasformazioneRepository trasformazioneRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public TrasformazioneService(TrasformazioneRepository trasformazioneRepo,
                                 ProductRepository productRepo,
                                 UserRepository userRepo) {
        this.trasformazioneRepo = trasformazioneRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public Prodotto creaProdottoTrasformato(CreateTransformedProductRequest req) {
        Utente u = userRepo.findById(req.getCreatoDaUserId())
                .orElseThrow(() -> new EntityNotFoundException("Utente " + req.getCreatoDaUserId() + " non trovato"));
        if (u.getStato() != StatoUtente.ATTIVO || u.getRuoloAssegnato() != Ruolo.TRASFORMATORE) {
            throw new IllegalStateException("Solo un TRASFORMATORE ATTIVO può creare prodotti trasformati");
        }


        List<Prodotto> base = productRepo.findAllById(req.getProdottiBaseIds());
        if (base.size() != req.getProdottiBaseIds().size()) {
            throw new EntityNotFoundException("Alcuni prodotti base non esistono");
        }
        for (Prodotto b : base) {
            if (b.getStato() != StatoProdotto.APPROVATO) {
                throw new IllegalStateException("Prodotto base " + b.getId() + " non è APPROVATO");
            }
            if (b.getTipo() == null || b.getTipo() != TipoProdotto.BASE) {
                throw new IllegalStateException("Prodotto " + b.getId() + " non è di tipo BASE");
            }
        }

        Prodotto trasformato = new Prodotto();
        trasformato.setNome(req.getNome());
        trasformato.setDescrizione(req.getDescrizione());
        trasformato.setLat(req.getLat());
        trasformato.setLon(req.getLon());
        trasformato.setCreatoDaUserId(req.getCreatoDaUserId());
        trasformato.setTipo(TipoProdotto.TRASFORMATO);
        trasformato.setStato(StatoProdotto.APPROVATO);
        trasformato.setNoteRifiuto(null);
        trasformato = productRepo.saveAndFlush(trasformato);

        Trasformazione t = new Trasformazione();
        t.setProdottiBase(base);
        t.setProdottoRisultante(trasformato);
        t.setCreatoDaUserId(req.getCreatoDaUserId());
        trasformazioneRepo.save(t);

        return trasformato;
    }

}
