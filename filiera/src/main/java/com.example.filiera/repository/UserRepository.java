package com.example.filiera.repository;

import com.example.filiera.domain.StatoUtente;
import com.example.filiera.domain.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByEmail(String email);
    List<Utente> findByStato(StatoUtente stato);
}
