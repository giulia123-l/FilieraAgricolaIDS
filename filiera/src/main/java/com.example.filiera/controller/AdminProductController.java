package com.example.filiera.controller;

import com.example.filiera.dto.ProductResponse;
import com.example.filiera.dto.RejectRequest;
import com.example.filiera.service.ProductService;
import com.example.filiera.domain.Prodotto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final ProductService service;

    public AdminProductController(ProductService service) {
        this.service = service;
    }

    @Operation(summary = "Approva un prodotto base, trasformato o pacchetto")
    @PostMapping("/{id}/approve")
    public ResponseEntity<ProductResponse> approve(@PathVariable("id") Long id,
                                                   @RequestParam("curatoreId") Long curatoreId) {
        Prodotto updated = service.approveById(id, curatoreId);
        return ResponseEntity.ok(ProductService.toResp(updated));
    }

    @Operation(summary = "Rifiuta un prodotto base, trasformato o pacchetto")
    @PostMapping("/{id}/reject")
    public ResponseEntity<ProductResponse> reject(@PathVariable("id") Long id,
                                                  @RequestParam("curatoreId") Long curatoreId,
                                                  @RequestBody @jakarta.validation.Valid RejectRequest body) {
        Prodotto updated = service.rejectById(id, curatoreId, body.getMotivazione());
        return ResponseEntity.ok(ProductService.toResp(updated));
    }

}
