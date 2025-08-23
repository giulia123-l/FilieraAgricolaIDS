package com.example.filiera.service;

import com.example.filiera.domain.*;
import com.example.filiera.dto.*;
import com.example.filiera.repository.*;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository pr, UserRepository ur){
        this.productRepository=pr;this.userRepository=ur;
    }

    @Transactional
    public Prodotto create(CreateProductRequest req){
        Utente u=userRepository.findById(req.creatoDaUserId)
          .orElseThrow(()->new IllegalArgumentException("Utente creatore non trovato"));
        if(u.getStato()!=StatoUtente.ATTIVO)
            throw new IllegalStateException("Utente non attivo");
        if(u.getRuoloAssegnato()==null ||
           !(u.getRuoloAssegnato()==Ruolo.PRODUTTORE||u.getRuoloAssegnato()==Ruolo.TRASFORMATORE||u.getRuoloAssegnato()==Ruolo.DISTRIBUTORE))
            throw new IllegalStateException("Ruolo non abilitato");
        Prodotto p=new Prodotto();
        p.setNome(req.nome);p.setDescrizione(req.descrizione);
        p.setLat(req.lat);p.setLon(req.lon);
        p.setCreatoDaUserId(u.getId());
        return productRepository.save(p);
    }

    public static ProductResponse toResp(Prodotto p){
        return new ProductResponse(p.getId(),p.getNome(),p.getDescrizione(),p.getLat(),p.getLon(),p.getStato(),p.getCreatoDaUserId());
    }
}
