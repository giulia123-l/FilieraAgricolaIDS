package com.example.filiera.factory;

import com.example.filiera.domain.Evento;
import com.example.filiera.domain.PrenotazioneEvento;

public class PrenotazioneFactory {
    public PrenotazioneEvento create(Evento evento, Long utenteId) {
        PrenotazioneEvento p = new PrenotazioneEvento();
        p.setEvento(evento);
        p.setUtenteId(utenteId);
        return p;
    }
}
