package com.example.filiera.dto;
import java.util.UUID;
import com.example.filiera.domain.StatoProdotto;

public class ProductResponse {
    public Long id;
    public String nome;
    public String descrizione;
    public Double lat;
    public Double lon;
    public StatoProdotto stato;
    public Long creatoDaUserId;
    public ProductResponse(Long id,String n,String d,Double lat,Double lon,StatoProdotto s,Long u){
        this.id=id;this.nome=n;this.descrizione=d;this.lat=lat;this.lon=lon;this.stato=s;this.creatoDaUserId=u;}
}
