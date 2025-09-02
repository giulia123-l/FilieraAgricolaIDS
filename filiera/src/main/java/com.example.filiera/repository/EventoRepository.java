package com.example.filiera.repository;

import com.example.filiera.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> { }
