package com.example.filiera.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateTransformedProductRequest {

    @NotEmpty
    private List<Long> prodottiBaseIds;

    @NotBlank
    private String nome;

    private String descrizione;
    private Double lat;
    private Double lon;

    @NotNull
    private Long creatoDaUserId;

    public List<Long> getProdottiBaseIds() { return prodottiBaseIds; }
    public void setProdottiBaseIds(List<Long> prodottiBaseIds) { this.prodottiBaseIds = prodottiBaseIds; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    public Double getLat() { return lat; }
    public void setLat(Double lat) { this.lat = lat; }
    public Double getLon() { return lon; }
    public void setLon(Double lon) { this.lon = lon; }
    public Long getCreatoDaUserId() { return creatoDaUserId; }
    public void setCreatoDaUserId(Long creatoDaUserId) { this.creatoDaUserId = creatoDaUserId; }
}
