package com.example.filiera.dto;

import jakarta.validation.constraints.NotNull;

public class CreatePrenotazioneRequest {
    @NotNull
    private Long eventoId;
    @NotNull
    private Long utenteId;

    public Long getEventoId() { return eventoId; }
    public void setEventoId(Long eventoId) { this.eventoId = eventoId; }
    public Long getUtenteId() { return utenteId; }
    public void setUtenteId(Long utenteId) { this.utenteId = utenteId; }
}
