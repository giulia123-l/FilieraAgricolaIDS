package com.example.filiera.service;

import com.example.filiera.domain.*;
import com.example.filiera.dto.MapPointResponse;
import com.example.filiera.repository.MarketplaceRepository;
import com.example.filiera.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappaService {

    private final ProductRepository productRepo;
    private final MarketplaceRepository marketplaceRepo;

    public MappaService(ProductRepository productRepo, MarketplaceRepository marketplaceRepo) {
        this.productRepo = productRepo;
        this.marketplaceRepo = marketplaceRepo;
    }

    public List<MapPointResponse> punti() {
        List<MapPointResponse> out = new ArrayList<>();
        var base = productRepo.findByStatoAndTipo(StatoProdotto.APPROVATO, TipoProdotto.BASE);
        for (Prodotto p : base) {
            if (p.getLat() != null && p.getLon() != null) {
                MapPointResponse m = new MapPointResponse();
                m.setTipo("PRODOTTO_BASE");
                m.setId(p.getId());
                m.setLabel(p.getNome());
                m.setLat(p.getLat());
                m.setLon(p.getLon());
                out.add(m);
            }
        }
        var trasf = productRepo.findByStatoAndTipo(StatoProdotto.APPROVATO, TipoProdotto.TRASFORMATO);
        for (Prodotto p : trasf) {
            if (p.getLat() != null && p.getLon() != null) {
                MapPointResponse m = new MapPointResponse();
                m.setTipo("PRODOTTO_TRASFORMATO");
                m.setId(p.getId());
                m.setLabel(p.getNome());
                m.setLat(p.getLat());
                m.setLon(p.getLon());
                out.add(m);
            }
        }
        var mkt = marketplaceRepo.findAll();
        for (Marketplace mk : mkt) {
            if (mk.getLat() != null && mk.getLon() != null) {
                MapPointResponse m = new MapPointResponse();
                m.setTipo("MARKETPLACE");
                m.setId(mk.getId());
                m.setLabel(mk.getNome());
                m.setLat(mk.getLat());
                m.setLon(mk.getLon());
                out.add(m);
            }
        }
        return out;
    }
}
