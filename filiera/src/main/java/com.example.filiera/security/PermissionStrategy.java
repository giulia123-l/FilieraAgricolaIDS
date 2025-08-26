package com.example.filiera.security;

import com.example.filiera.domain.Utente;

public interface PermissionStrategy {
    void check(Utente utente);
}
