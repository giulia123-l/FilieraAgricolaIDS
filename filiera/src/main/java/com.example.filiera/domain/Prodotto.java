package com.example.filiera.domain;

import jakarta.persistence.*;

@Entity
@Table(name="prodotti")
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nome;

    @Column(length=2000)
    private String descrizione;

    private Double lat;
    private Double lon;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato", nullable = false, length = 40, columnDefinition = "VARCHAR(40)")
    private StatoProdotto stato = StatoProdotto.IN_ATTESA_APPROVAZIONE;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 20, columnDefinition = "VARCHAR(20)")
    private TipoProdotto tipo;

    @Column(length=2000)
    private String noteRifiuto;

    @Column(nullable=false)
    private Long creatoDaUserId;

    @PrePersist
    public void prePersist() {
        if (stato == null) stato = StatoProdotto.IN_ATTESA_APPROVAZIONE;
        noteRifiuto = null;
    }

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
    public TipoProdotto getTipo() { return tipo; }          // <-- getter
    public void setTipo(TipoProdotto tipo) { this.tipo = tipo; } // <-- setter
    public String getNoteRifiuto() { return noteRifiuto; }
    public void setNoteRifiuto(String noteRifiuto) { this.noteRifiuto = noteRifiuto; }
    public StatoProdotto getStato(){return stato;}
    public void setStato(StatoProdotto s){this.stato=s;}
}
