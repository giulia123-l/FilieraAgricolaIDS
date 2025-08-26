package com.example.filiera.domain;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=200)
    private String titolo;

    @Column(length=2000)
    private String descrizione;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=30)
    private TipoEvento tipo;

    @Column(nullable=false)
    private OffsetDateTime inizio;

    @Column(nullable=false)
    private OffsetDateTime fine;

    private Double lat;
    private Double lon;

    @Column(length=400)
    private String indirizzo;

    @Column(nullable=false)
    private Long creatoDaUserId;

    public Long getId() { return id; }
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
