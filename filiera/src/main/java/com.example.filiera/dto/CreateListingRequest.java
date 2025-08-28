package com.example.filiera.dto;

import com.example.filiera.domain.TipoOfferta;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateListingRequest {
    @NotNull
    private Long marketplaceId;
    @NotNull
    private TipoOfferta tipo;
    private Long prodottoId;
    private Long pacchettoId;
    @NotNull
    private BigDecimal prezzo;
    @NotNull @Min(0)
    private Integer stock;

    public Long getMarketplaceId() { return marketplaceId; }
    public void setMarketplaceId(Long marketplaceId) { this.marketplaceId = marketplaceId; }
    public TipoOfferta getTipo() { return tipo; }
    public void setTipo(TipoOfferta tipo) { this.tipo = tipo; }
    public Long getProdottoId() { return prodottoId; }
    public void setProdottoId(Long prodottoId) { this.prodottoId = prodottoId; }
    public Long getPacchettoId() { return pacchettoId; }
    public void setPacchettoId(Long pacchettoId) { this.pacchettoId = pacchettoId; }
    public BigDecimal getPrezzo() { return prezzo; }
    public void setPrezzo(BigDecimal prezzo) { this.prezzo = prezzo; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
