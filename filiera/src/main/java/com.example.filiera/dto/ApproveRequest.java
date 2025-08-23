package com.example.filiera.dto;

import com.example.filiera.domain.Ruolo;
import jakarta.validation.constraints.NotNull;

public class ApproveRequest {
    @NotNull
    public Ruolo ruoloAssegnato;
}
