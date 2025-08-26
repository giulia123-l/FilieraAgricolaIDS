package com.example.filiera.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ordini_item")
public class OrdineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name = "ordine_id", nullable = false)
    private Ordine ordine;

    @ManyToOne(optional=false)
    @JoinColumn(name = "offerta_id", nullable = false)
    private Offerta offerta;

    @Column(nullable=false)
    private Integer quantita;

    @Column(nullable=false, precision=15, scale=2)
    private BigDecimal prezzoUnitario;

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal totaleRiga;

    public Long getId() { return id; }
    public Ordine getOrdine() { return ordine; }
    public void setOrdine(Ordine ordine) { this.ordine = ordine; }
    public Offerta getOfferta() { return offerta; }
    public void setOfferta(Offerta offerta) { this.offerta = offerta; }
    public Integer getQuantita() { return quantita; }
    public void setQuantita(Integer quantita) { this.quantita = quantita; }
    public BigDecimal getPrezzoUnitario() { return prezzoUnitario; }
    public void setPrezzoUnitario(BigDecimal prezzoUnitario) { this.prezzoUnitario = prezzoUnitario; }
    public BigDecimal getTotaleRiga() { return totaleRiga; }
    public void setTotaleRiga(BigDecimal totaleRiga) { this.totaleRiga = totaleRiga; }
}
