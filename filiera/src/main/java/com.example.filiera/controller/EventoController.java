package com.example.filiera.controller;

import com.example.filiera.domain.Evento;
import com.example.filiera.dto.CreateEventoRequest;
import com.example.filiera.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eventi")
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @Operation(summary="Crea evento o visita aziendale")
    @PostMapping
    public ResponseEntity<Evento> create(@RequestBody @Valid CreateEventoRequest req) {
        return ResponseEntity.ok(service.create(req));
    }
}
