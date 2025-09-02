package com.example.filiera.dto;

import jakarta.validation.constraints.NotNull;

public class CreateShareLinkRequest {
    @NotNull
    private Long prodottoId;
    @NotNull
    private Long creatoDaUserId;

    public Long getProdottoId() { return prodottoId; }
    public void setProdottoId(Long prodottoId) { this.prodottoId = prodottoId; }
    public Long getCreatoDaUserId() { return creatoDaUserId; }
    public void setCreatoDaUserId(Long creatoDaUserId) { this.creatoDaUserId = creatoDaUserId; }
}
