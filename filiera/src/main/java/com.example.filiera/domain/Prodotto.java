package com.example.filiera.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name="prodotti")
public class Prodotto {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String nome;

    @Column(length=2000)
    private String descrizione;

    private Double lat;
    private Double lon;

    @Enumerated(EnumType.STRING)
    private StatoProdotto stato = StatoProdotto.IN_ATTESA_APPROVAZIONE;

    private Long creatoDaUserId;

    public Long getId(){return id;}
    public String getNome(){return nome;}
    public void setNome(String n){this.nome=n;}
    public String getDescrizione(){return descrizione;}
    public void setDescrizione(String d){this.descrizione=d;}
    public Double getLat(){return lat;}
    public void setLat(Double l){this.lat=l;}
    public Double getLon(){return lon;}
    public void setLon(Double l){this.lon=l;}
    public Long getCreatoDaUserId(){return creatoDaUserId;}
    public void setCreatoDaUserId(Long id){this.creatoDaUserId=id;}
    public StatoProdotto getStato(){return stato;}
    public void setStato(StatoProdotto s){this.stato=s;}
}
