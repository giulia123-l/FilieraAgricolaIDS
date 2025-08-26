package com.example.filiera.controller;

import com.example.filiera.dto.CreateOrderRequest;
import com.example.filiera.dto.OrderResponse;
import com.example.filiera.service.OrdineService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordini")
public class OrdineController {

    private final OrdineService service;

    public OrdineController(OrdineService service) {
        this.service = service;
    }

    @Operation(summary="Crea un ordine di prodotti/pacchetti")
    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid CreateOrderRequest req) {
        return ResponseEntity.ok(service.createOrder(req));
    }
}
