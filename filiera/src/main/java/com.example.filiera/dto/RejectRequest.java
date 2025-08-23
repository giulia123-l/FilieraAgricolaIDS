package com.example.filiera.dto;

import jakarta.validation.constraints.NotBlank;

public class RejectRequest {
    @NotBlank
    public String motivazione;

    public String getMotivazione() { return motivazione; }
    public void setMotivazione(String motivazione) { this.motivazione = motivazione; }

}
