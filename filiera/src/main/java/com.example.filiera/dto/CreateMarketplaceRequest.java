package com.example.filiera.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateMarketplaceRequest {
    @NotBlank
    private String nome;
    private String descrizione;
    private String indirizzo;
    private Double lat;
    private Double lon;
    private String orari;
    @NotNull
    private Long creatoDaUserId;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    public String getIndirizzo() { return indirizzo; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }
    public Double getLat() { return lat; }
    public void setLat(Double lat) { this.lat = lat; }
    public Double getLon() { return lon; }
    public void setLon(Double lon) { this.lon = lon; }
    public String getOrari() { return orari; }
    public void setOrari(String orari) { this.orari = orari; }
    public Long getCreatoDaUserId() { return creatoDaUserId; }
    public void setCreatoDaUserId(Long creatoDaUserId) { this.creatoDaUserId = creatoDaUserId; }
}
