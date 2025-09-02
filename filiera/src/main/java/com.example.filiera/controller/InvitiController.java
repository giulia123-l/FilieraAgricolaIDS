package com.example.filiera.controller;

import com.example.filiera.domain.InvitoEvento;
import com.example.filiera.dto.CreateInvitiRequest;
import com.example.filiera.service.InvitoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/eventi/inviti")
public class InvitiController {

    private final InvitoService service;

    public InvitiController(InvitoService service) {
        this.service = service;
    }

    @Operation(summary="Invita utenti a un evento")
    @PostMapping
    public ResponseEntity<List<InvitoEvento>> invita(@RequestBody @Valid CreateInvitiRequest req) {
        return ResponseEntity.ok(service.invita(req));
    }
}
