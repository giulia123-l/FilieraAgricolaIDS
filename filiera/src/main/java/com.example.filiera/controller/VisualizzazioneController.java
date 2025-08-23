package com.example.filiera.controller;

import com.example.filiera.domain.Pacchetto;
import com.example.filiera.domain.Prodotto;
import com.example.filiera.service.VisualizzazioneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visualizzazione")
public class VisualizzazioneController {

    private final VisualizzazioneService service;

    public VisualizzazioneController(VisualizzazioneService service) {
        this.service = service;
    }

    @GetMapping("/prodotti/base")
    public ResponseEntity<List<Prodotto>> base() {
        return ResponseEntity.ok(service.prodottiBaseApprovati());
    }

    @GetMapping("/prodotti/trasformati")
    public ResponseEntity<List<Prodotto>> trasformati() {
        return ResponseEntity.ok(service.prodottiTrasformatiApprovati());
    }

    @GetMapping("/pacchetti")
    public ResponseEntity<List<Pacchetto>> pacchetti() {
        return ResponseEntity.ok(service.pacchetti());
    }
}
