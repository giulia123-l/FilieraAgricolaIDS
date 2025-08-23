package com.example.filiera.controller;

import com.example.filiera.domain.Prodotto;
import com.example.filiera.dto.CreateTransformedProductRequest;
import com.example.filiera.dto.ProductResponse;
import com.example.filiera.service.ProductService;
import com.example.filiera.service.TrasformazioneService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/transformazioni")
public class TrasformazioneController {

    private final TrasformazioneService service;

    public TrasformazioneController(TrasformazioneService service) {
        this.service = service;
    }

    @Operation(summary="Carica un prodotto trasformato")
    @PostMapping
    public ResponseEntity<ProductResponse> creaTrasformato(@RequestBody @Valid CreateTransformedProductRequest req) {
        Prodotto p = service.creaProdottoTrasformato(req);
        return ResponseEntity.ok(ProductService.toResp(p));
    }
}
