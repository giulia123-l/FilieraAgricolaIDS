package com.example.filiera.dto;

import java.util.List;

public class CreatePackageRequest {
    private String nome;
    private List<Long> productIds;
    private Long creatoDaUserId;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Long> getProductIds() { return productIds; }
    public void setProductIds(List<Long> productIds) { this.productIds = productIds; }

    public Long getCreatoDaUserId() { return creatoDaUserId; }
    public void setCreatoDaUserId(Long creatoDaUserId) { this.creatoDaUserId = creatoDaUserId; }
}
