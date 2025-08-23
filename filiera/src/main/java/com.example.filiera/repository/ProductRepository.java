package com.example.filiera.repository;

import com.example.filiera.domain.Prodotto;
import com.example.filiera.domain.StatoProdotto;
import com.example.filiera.domain.TipoProdotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Prodotto, Long> {
    List<Prodotto> findByStato(StatoProdotto stato);
    List<Prodotto> findByStatoAndTipo(StatoProdotto stato, TipoProdotto tipo);

}
