package com.example.filiera.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "offerte")
public class Offerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name = "marketplace_id", nullable = false)
    private Marketplace marketplace;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private TipoOfferta tipo;

    @ManyToOne
    @JoinColumn(name = "prodotto_id")
    private Prodotto prodotto;

    @ManyToOne
    @JoinColumn(name = "pacchetto_id")
    private Pacchetto pacchetto;

    @Column(nullable=false, precision = 15, scale = 2)
    private BigDecimal prezzo;

    @Column(nullable=false)
    private Integer stock;

    public Long getId() { return id; }
    public Marketplace getMarketplace() { return marketplace; }
    public void setMarketplace(Marketplace marketplace) { this.marketplace = marketplace; }
    public TipoOfferta getTipo() { return tipo; }
    public void setTipo(TipoOfferta tipo) { this.tipo = tipo; }
    public Prodotto getProdotto() { return prodotto; }
    public void setProdotto(Prodotto prodotto) { this.prodotto = prodotto; }
    public Pacchetto getPacchetto() { return pacchetto; }
    public void setPacchetto(Pacchetto pacchetto) { this.pacchetto = pacchetto; }
    public BigDecimal getPrezzo() { return prezzo; }
    public void setPrezzo(BigDecimal prezzo) { this.prezzo = prezzo; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
