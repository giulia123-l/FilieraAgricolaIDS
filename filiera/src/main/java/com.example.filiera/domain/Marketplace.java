package com.example.filiera.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "marketplace")
public class Marketplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=200)
    private String nome;

    @Column(length=2000)
    private String descrizione;

    @Column(length=400)
    private String indirizzo;

    private Double lat;
    private Double lon;

    @Column(length=400)
    private String orari;

    @Column(nullable=false)
    private Long creatoDaUserId;

    public Long getId() { return id; }
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
