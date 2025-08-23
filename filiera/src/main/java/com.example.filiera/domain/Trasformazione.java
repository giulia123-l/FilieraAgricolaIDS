package com.example.filiera.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "trasformazioni")
public class Trasformazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToMany
    @JoinTable(
            name = "trasformazione_input",
            joinColumns = @JoinColumn(name = "trasformazione_id"),
            inverseJoinColumns = @JoinColumn(name = "prodotto_base_id")
    )
    private List<Prodotto> prodottiBase;

    @OneToOne
    @JoinColumn(name = "prodotto_risultante_id", nullable = false)
    private Prodotto prodottoRisultante;

    @Column(nullable = false)
    private Long creatoDaUserId;

    public Long getId() { return id; }
    public List<Prodotto> getProdottiBase() { return prodottiBase; }
    public void setProdottiBase(List<Prodotto> prodottiBase) { this.prodottiBase = prodottiBase; }
    public Prodotto getProdottoRisultante() { return prodottoRisultante; }
    public void setProdottoRisultante(Prodotto prodottoRisultante) { this.prodottoRisultante = prodottoRisultante; }
    public Long getCreatoDaUserId() { return creatoDaUserId; }
    public void setCreatoDaUserId(Long creatoDaUserId) { this.creatoDaUserId = creatoDaUserId; }
}
