package com.example.filiera.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class CreateProductRequest {
    @NotNull public Long creatoDaUserId;
    @NotBlank public String nome;
    public String descrizione;
    @NotNull public Double lat;
    @NotNull public Double lon;
}
