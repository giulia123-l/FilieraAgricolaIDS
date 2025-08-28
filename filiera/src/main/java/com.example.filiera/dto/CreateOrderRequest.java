package com.example.filiera.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class CreateOrderRequest {
    @NotNull
    private Long acquirenteId;
    @NotNull
    private Long marketplaceId;
    @NotNull
    private List<Item> items;

    public static class Item {
        @NotNull
        private Long listingId;
        @NotNull @Min(1)
        private Integer quantita;

        public Long getListingId() { return listingId; }
        public void setListingId(Long listingId) { this.listingId = listingId; }
        public Integer getQuantita() { return quantita; }
        public void setQuantita(Integer quantita) { this.quantita = quantita; }
    }

    public Long getAcquirenteId() { return acquirenteId; }
    public void setAcquirenteId(Long acquirenteId) { this.acquirenteId = acquirenteId; }
    public Long getMarketplaceId() { return marketplaceId; }
    public void setMarketplaceId(Long marketplaceId) { this.marketplaceId = marketplaceId; }
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
}
