package com.example.filiera.factory;

import com.example.filiera.domain.*;
import com.example.filiera.dto.CreateListingRequest;

public class OffertaFactory {
    public Offerta create(CreateListingRequest req, Marketplace marketplace, Prodotto prodotto, Pacchetto pacchetto) {
        Offerta o = new Offerta();
        o.setMarketplace(marketplace);
        o.setTipo(req.getTipo());
        o.setProdotto(prodotto);
        o.setPacchetto(pacchetto);
        o.setPrezzo(req.getPrezzo());
        o.setStock(req.getStock());
        return o;
    }
}
