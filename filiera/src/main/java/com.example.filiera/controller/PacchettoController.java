package com.example.filiera.controller;

import com.example.filiera.domain.Pacchetto;
import com.example.filiera.dto.CreatePackageRequest;
import com.example.filiera.service.PacchettoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pacchetti")
public class PacchettoController {

    private final PacchettoService service;

    public PacchettoController(PacchettoService service) {
        this.service = service;
    }
    @Operation(summary="Carica un pacchetto")
    @PostMapping
    public ResponseEntity<Pacchetto> create(@RequestBody CreatePackageRequest req) {
        return ResponseEntity.ok(service.create(req));
    }
}
