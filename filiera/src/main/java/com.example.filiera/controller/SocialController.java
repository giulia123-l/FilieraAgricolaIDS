package com.example.filiera.controller;

import com.example.filiera.dto.CreateShareLinkRequest;
import com.example.filiera.dto.ShareLinkResponse;
import com.example.filiera.service.SocialShareService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/social")
public class SocialController {

    private final SocialShareService service;

    public SocialController(SocialShareService service) {
        this.service = service;
    }

    @Operation(summary="Crea un link di condivisione per un prodotto")
    @PostMapping("/links")
    public ResponseEntity<ShareLinkResponse> createLink(@RequestBody @Valid CreateShareLinkRequest req) {
        return ResponseEntity.ok(service.createLink(req));
    }

    @Operation(summary="Registra un click su un link di condivisione")
    @PostMapping("/links/{token}/click")
    public ResponseEntity<Void> trackClick(@PathVariable("token") String token) {
        service.trackClick(token);
        return ResponseEntity.ok().build();
    }

    @Operation(summary="Storico link creati da un utente")
    @GetMapping("/links")
    public ResponseEntity<List<ShareLinkResponse>> list(@RequestParam("creatoDaUserId") Long creatoDaUserId) {
        return ResponseEntity.ok(service.listByCreator(creatoDaUserId));
    }
}
