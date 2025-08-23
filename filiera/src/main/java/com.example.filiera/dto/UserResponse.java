package com.example.filiera.dto;

import com.example.filiera.domain.Ruolo;
import com.example.filiera.domain.StatoUtente;
import java.util.UUID;

public class UserResponse {
    public Long id;
    public String nome;
    public String cognome;
    public String email;
    public StatoUtente stato;
    public Ruolo ruoloAssegnato;

    public UserResponse(Long id, String nome, String cognome, String email, StatoUtente stato, Ruolo ruoloAssegnato) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.stato = stato;
        this.ruoloAssegnato = ruoloAssegnato;
    }
}
