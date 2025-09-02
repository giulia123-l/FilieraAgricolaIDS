package com.example.filiera.repository;

import com.example.filiera.domain.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineRepository extends JpaRepository<Ordine, Long> { }
