package com.example.filiera.dto;

import com.example.filiera.domain.TipoEvento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public class CreateEventoRequest {
    @NotBlank
    private String titolo;
    private String descrizione;
    @NotNull
    private TipoEvento tipo;
    @NotNull
    private OffsetDateTime inizio;
    @NotNull
    private OffsetDateTime fine;
    private Double lat;
    private Double lon;
    private String indirizzo;
    @NotNull
    private Long creatoDaUserId;

    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }
    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    public TipoEvento getTipo() { return tipo; }
    public void setTipo(TipoEvento tipo) { this.tipo = tipo; }
    public OffsetDateTime getInizio() { return inizio; }
    public void setInizio(OffsetDateTime inizio) { this.inizio = inizio; }
    public OffsetDateTime getFine() { return fine; }
    public void setFine(OffsetDateTime fine) { this.fine = fine; }
    public Double getLat() { return lat; }
    public void setLat(Double lat) { this.lat = lat; }
    public Double getLon() { return lon; }
    public void setLon(Double lon) { this.lon = lon; }
    public String getIndirizzo() { return indirizzo; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }
    public Long getCreatoDaUserId() { return creatoDaUserId; }
    public void setCreatoDaUserId(Long creatoDaUserId) { this.creatoDaUserId = creatoDaUserId; }
}
