package com.example.filiera.controller;

import com.example.filiera.dto.LoginRequest;
import com.example.filiera.dto.LoginResponse;
import com.example.filiera.dto.RegisterRequest;
import com.example.filiera.dto.UserResponse;
import com.example.filiera.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Auth")
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Registra un nuovo utente")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        var saved = userService.register(request);
        return ResponseEntity.ok(UserService.toResponse(saved));
    }

    @Operation(summary = "Autentica un utente attivo e restituisce un token")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        var resp = userService.authenticate(request);
        return ResponseEntity.ok(resp);
    }
}
