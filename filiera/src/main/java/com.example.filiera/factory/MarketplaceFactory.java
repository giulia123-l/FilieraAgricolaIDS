package com.example.filiera.factory;

import com.example.filiera.domain.Marketplace;
import com.example.filiera.dto.CreateMarketplaceRequest;

public class MarketplaceFactory {
    public Marketplace create(CreateMarketplaceRequest req) {
        Marketplace m = new Marketplace();
        m.setNome(req.getNome());
        m.setDescrizione(req.getDescrizione());
        m.setIndirizzo(req.getIndirizzo());
        m.setLat(req.getLat());
        m.setLon(req.getLon());
        m.setOrari(req.getOrari());
        m.setCreatoDaUserId(req.getCreatoDaUserId());
        return m;
    }
}
