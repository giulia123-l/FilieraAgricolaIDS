package com.example.filiera.service;

import com.example.filiera.domain.Evento;
import com.example.filiera.domain.PrenotazioneEvento;
import com.example.filiera.domain.Utente;
import com.example.filiera.dto.CreatePrenotazioneRequest;
import com.example.filiera.factory.PrenotazioneFactory;
import com.example.filiera.repository.EventoRepository;
import com.example.filiera.repository.PrenotazioneEventoRepository;
import com.example.filiera.repository.UserRepository;
import com.example.filiera.security.Action;
import com.example.filiera.security.PermissionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {

    private final EventoRepository eventoRepo;
    private final UserRepository userRepo;
    private final PrenotazioneEventoRepository prenRepo;
    private final PermissionService permissionService = new PermissionService();
    private final PrenotazioneFactory factory = new PrenotazioneFactory();

    public PrenotazioneService(EventoRepository eventoRepo, UserRepository userRepo, PrenotazioneEventoRepository prenRepo) {
        this.eventoRepo = eventoRepo;
        this.userRepo = userRepo;
        this.prenRepo = prenRepo;
    }

    @Transactional
    public PrenotazioneEvento prenota(CreatePrenotazioneRequest req) {
        Utente u = userRepo.findById(req.getUtenteId()).orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));
        permissionService.strategyFor(Action.BOOK_EVENT).check(u);
        Evento e = eventoRepo.findById(req.getEventoId()).orElseThrow(() -> new EntityNotFoundException("Evento non trovato"));
        PrenotazioneEvento p = factory.create(e, u.getId());
        return prenRepo.save(p);
    }
}
