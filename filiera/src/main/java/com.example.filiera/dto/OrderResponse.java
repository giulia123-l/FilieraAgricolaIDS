package com.example.filiera.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponse {
    private Long id;
    private String stato;
    private BigDecimal totale;
    private List<Row> righe;

    public static class Row {
        private Long listingId;
        private Integer quantita;
        private BigDecimal prezzoUnitario;
        private BigDecimal totaleRiga;

        public Long getListingId() { return listingId; }
        public void setListingId(Long listingId) { this.listingId = listingId; }
        public Integer getQuantita() { return quantita; }
        public void setQuantita(Integer quantita) { this.quantita = quantita; }
        public BigDecimal getPrezzoUnitario() { return prezzoUnitario; }
        public void setPrezzoUnitario(BigDecimal prezzoUnitario) { this.prezzoUnitario = prezzoUnitario; }
        public BigDecimal getTotaleRiga() { return totaleRiga; }
        public void setTotaleRiga(BigDecimal totaleRiga) { this.totaleRiga = totaleRiga; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }
    public BigDecimal getTotale() { return totale; }
    public void setTotale(BigDecimal totale) { this.totale = totale; }
    public List<Row> getRighe() { return righe; }
    public void setRighe(List<Row> righe) { this.righe = righe; }
}
