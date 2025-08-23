package com.example.filiera.service;

import com.example.filiera.domain.Utente;
import com.example.filiera.dto.LoginResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final Map<String, SessionToken> tokenStore = new ConcurrentHashMap<>();

    public String hashPassword(String plain) {
        return encoder.encode(plain);
    }

    public boolean verifyPassword(String plain, String hash) {
        return encoder.matches(plain, hash);
    }

    public LoginResponse createToken(Utente user) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, new SessionToken(token, user.getId(), OffsetDateTime.now()));
        return new LoginResponse(token, user.getId(), user.getRuoloAssegnato());
    }

    public record SessionToken(String token, Long userId, OffsetDateTime issuedAt) {}
}
