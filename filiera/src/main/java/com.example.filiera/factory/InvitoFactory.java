package com.example.filiera.factory;

import com.example.filiera.domain.Evento;
import com.example.filiera.domain.InvitoEvento;

public class InvitoFactory {
    public InvitoEvento create(Evento evento, Long invitatoUserId, Long inviatoDaUserId) {
        InvitoEvento i = new InvitoEvento();
        i.setEvento(evento);
        i.setInvitatoUserId(invitatoUserId);
        i.setInviatoDaUserId(inviatoDaUserId);
        return i;
    }
}
