package com.example.filiera.controller;

import com.example.filiera.domain.PrenotazioneEvento;
import com.example.filiera.dto.CreatePrenotazioneRequest;
import com.example.filiera.service.PrenotazioneService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/eventi/prenotazioni")
public class PrenotazioniController {

    private final PrenotazioneService service;

    public PrenotazioniController(PrenotazioneService service) {
        this.service = service;
    }

    @Operation(summary="Prenota un evento o una visita")
    @PostMapping
    public ResponseEntity<PrenotazioneEvento> prenota(@RequestBody @Valid CreatePrenotazioneRequest req) {
        return ResponseEntity.ok(service.prenota(req));
    }
}
