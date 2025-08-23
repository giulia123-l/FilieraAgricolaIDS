package com.example.filiera.service;

import com.example.filiera.domain.*;
import com.example.filiera.dto.*;
import com.example.filiera.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthService authService;

    public UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Transactional
    public Utente register(@Valid RegisterRequest request) {
        userRepository.findByEmail(request.email).ifPresent(u -> {
            throw new IllegalArgumentException("Email già registrata");
        });

        Utente u = new Utente();
        u.setNome(request.nome);
        u.setCognome(request.cognome);
        u.setEmail(request.email);
        u.setPasswordHash(authService.hashPassword(request.password));
        u.setRuoloRichiesto(request.ruoloRichiesto);
        u.setStato(StatoUtente.IN_ATTESA_APPROVAZIONE);
        return userRepository.save(u);
    }

    @Transactional
    public Utente approve(Long userId, Ruolo ruoloAssegnato) {
        Utente u = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));
        if (u.getStato() != StatoUtente.IN_ATTESA_APPROVAZIONE) {
            throw new IllegalStateException("Utente non in stato approvabile");
        }
        u.setRuoloAssegnato(ruoloAssegnato);
        u.setStato(StatoUtente.ATTIVO);
        u.setNoteRifiuto(null);
        return userRepository.save(u);
    }

    @Transactional
    public Utente reject(Long userId, String motivazione) {
        Utente u = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));
        if (u.getStato() != StatoUtente.IN_ATTESA_APPROVAZIONE) {
            throw new IllegalStateException("Utente non in stato rifiutabile");
        }
        u.setStato(StatoUtente.RIFIUTATO);
        u.setNoteRifiuto(motivazione);
        return userRepository.save(u);
    }

    public List<Utente> listPending() {
        return userRepository.findByStato(StatoUtente.IN_ATTESA_APPROVAZIONE);
    }

    public LoginResponse authenticate(LoginRequest request) {
        Utente u = userRepository.findByEmail(request.email).orElseThrow(() -> new IllegalArgumentException("Credenziali non valide"));
        if (u.getStato() != StatoUtente.ATTIVO) {
            throw new IllegalStateException("Utente non attivo");
        }
        if (!authService.verifyPassword(request.password, u.getPasswordHash())) {
            throw new IllegalArgumentException("Credenziali non valide");
        }
        return authService.createToken(u);
    }

    public static UserResponse toResponse(Utente u) {
        return new UserResponse(u.getId(), u.getNome(), u.getCognome(), u.getEmail(), u.getStato(), u.getRuoloAssegnato());
    }
}
