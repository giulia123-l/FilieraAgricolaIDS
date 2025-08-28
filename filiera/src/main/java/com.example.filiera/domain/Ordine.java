package com.example.filiera.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordini")
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name = "marketplace_id", nullable = false)
    private Marketplace marketplace;

    @Column(nullable=false)
    private Long acquirenteId;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private StatoOrdine stato = StatoOrdine.CREATO;

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal totale = BigDecimal.ZERO;

    @Column(nullable=false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdineItem> items = new ArrayList<>();

    public Long getId() { return id; }
    public Marketplace getMarketplace() { return marketplace; }
    public void setMarketplace(Marketplace marketplace) { this.marketplace = marketplace; }
    public Long getAcquirenteId() { return acquirenteId; }
    public void setAcquirenteId(Long acquirenteId) { this.acquirenteId = acquirenteId; }
    public StatoOrdine getStato() { return stato; }
    public void setStato(StatoOrdine stato) { this.stato = stato; }
    public BigDecimal getTotale() { return totale; }
    public void setTotale(BigDecimal totale) { this.totale = totale; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public List<OrdineItem> getItems() { return items; }
    public void setItems(List<OrdineItem> items) { this.items = items; }
}
