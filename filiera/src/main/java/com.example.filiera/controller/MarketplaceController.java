package com.example.filiera.controller;

import com.example.filiera.domain.Offerta;
import com.example.filiera.dto.CreateListingRequest;
import com.example.filiera.dto.CreateMarketplaceRequest;
import com.example.filiera.service.MarketplaceService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.filiera.domain.Marketplace;

@RestController
@RequestMapping("/api/marketplaces")
public class MarketplaceController {

    private final MarketplaceService service;

    public MarketplaceController(MarketplaceService service) {
        this.service = service;
    }

    @Operation(summary="Crea un marketplace")
    @PostMapping
    public ResponseEntity<Marketplace> create(@RequestBody @Valid CreateMarketplaceRequest req) {
        return ResponseEntity.ok(service.createMarketplace(req));
    }

    @Operation(summary="Aggiunge una offerta al marketplace")
    @PostMapping("/listings")
    public ResponseEntity<Offerta> addListing(@RequestBody @Valid CreateListingRequest req) {
        return ResponseEntity.ok(service.addListing(req));
    }
}
