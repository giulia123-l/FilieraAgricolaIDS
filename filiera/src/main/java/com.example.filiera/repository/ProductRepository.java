package com.example.filiera.repository;
import com.example.filiera.domain.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface ProductRepository extends JpaRepository<Prodotto, Long> {}
