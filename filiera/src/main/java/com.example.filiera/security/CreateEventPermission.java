package com.example.filiera.security;

import com.example.filiera.domain.Ruolo;
import com.example.filiera.domain.StatoUtente;
import com.example.filiera.domain.Utente;

public class CreateEventPermission implements PermissionStrategy {
    @Override
    public void check(Utente u) {
        if (u == null) throw new IllegalStateException("Utente mancante");
        if (u.getStato() != StatoUtente.ATTIVO) throw new IllegalStateException("Utente non attivo");
        if (u.getRuoloAssegnato() != Ruolo.ANIMATORE) throw new IllegalStateException("Solo ANIMATORE può creare eventi");
    }
}
