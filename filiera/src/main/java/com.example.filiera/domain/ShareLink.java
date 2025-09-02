package com.example.filiera.domain;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "share_links")
public class ShareLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private Long creatoDaUserId;

    @ManyToOne(optional=false)
    @JoinColumn(name = "prodotto_id", nullable = false)
    private Prodotto prodotto;

    @Column(nullable=false, unique = true, length = 60)
    private String token;

    @Column(nullable=false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(nullable=false)
    private Long clickCount = 0L;

    @PrePersist
    public void prePersist() {
        if (token == null) token = UUID.randomUUID().toString();
    }

    public Long getId() { return id; }
    public Long getCreatoDaUserId() { return creatoDaUserId; }
    public void setCreatoDaUserId(Long creatoDaUserId) { this.creatoDaUserId = creatoDaUserId; }
    public Prodotto getProdotto() { return prodotto; }
    public void setProdotto(Prodotto prodotto) { this.prodotto = prodotto; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public Long getClickCount() { return clickCount; }
    public void setClickCount(Long clickCount) { this.clickCount = clickCount; }
}
