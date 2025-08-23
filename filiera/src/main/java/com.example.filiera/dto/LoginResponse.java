package com.example.filiera.dto;

import java.util.UUID;
import com.example.filiera.domain.Ruolo;

public class LoginResponse {
    public String token;
    public Long userId;
    public Ruolo ruolo;

    public LoginResponse(String token, Long userId, Ruolo ruolo) {
        this.token = token;
        this.userId = userId;
        this.ruolo = ruolo;
    }
}
