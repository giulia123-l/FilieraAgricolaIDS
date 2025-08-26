package com.example.filiera.security;

import com.example.filiera.domain.Ruolo;
import com.example.filiera.domain.StatoUtente;
import com.example.filiera.domain.Utente;

public class AddListingPermission implements PermissionStrategy {
    @Override
    public void check(Utente u) {
        if (u == null) throw new IllegalStateException("Utente mancante");
        if (u.getStato() != StatoUtente.ATTIVO) throw new IllegalStateException("Utente non attivo");
        var r = u.getRuoloAssegnato();
        if (r != Ruolo.DISTRIBUTORE && r != Ruolo.GESTORE) throw new IllegalStateException("Solo DISTRIBUTORE o GESTORE può aggiungere offerte");
    }
}
