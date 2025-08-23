package com.example.filiera.service;

import com.example.filiera.domain.*;
import com.example.filiera.repository.PacchettoRepository;
import com.example.filiera.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisualizzazioneService {

    private final ProductRepository prodottoRepo;
    private final PacchettoRepository pacchettoRepo;

    public VisualizzazioneService(ProductRepository prodottoRepo, PacchettoRepository pacchettoRepo) {
        this.prodottoRepo = prodottoRepo;
        this.pacchettoRepo = pacchettoRepo;
    }

    public List<Prodotto> prodottiBaseApprovati() {
        return prodottoRepo.findByStatoAndTipo(StatoProdotto.APPROVATO, TipoProdotto.BASE);
    }

    public List<Prodotto> prodottiTrasformatiApprovati() {
        return prodottoRepo.findByStatoAndTipo(StatoProdotto.APPROVATO, TipoProdotto.TRASFORMATO);
    }

    public List<Pacchetto> pacchetti() {
        return pacchettoRepo.findAll();
    }
}
