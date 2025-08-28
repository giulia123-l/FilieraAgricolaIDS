package com.example.filiera.service;

import com.example.filiera.domain.*;
import com.example.filiera.dto.CreateEventoRequest;
import com.example.filiera.repository.EventoRepository;
import com.example.filiera.repository.UserRepository;
import com.example.filiera.security.Action;
import com.example.filiera.security.PermissionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    private final EventoRepository eventoRepo;
    private final UserRepository userRepo;
    private final PermissionService permissionService = new PermissionService();

    public EventoService(EventoRepository eventoRepo, UserRepository userRepo) {
        this.eventoRepo = eventoRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public Evento create(CreateEventoRequest req) {
        Utente u = userRepo.findById(req.getCreatoDaUserId()).orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));
        permissionService.strategyFor(Action.CREATE_EVENT).check(u);
        Evento e = new Evento();
        e.setTitolo(req.getTitolo());
        e.setDescrizione(req.getDescrizione());
        e.setTipo(req.getTipo());
        e.setInizio(req.getInizio());
        e.setFine(req.getFine());
        e.setLat(req.getLat());
        e.setLon(req.getLon());
        e.setIndirizzo(req.getIndirizzo());
        e.setCreatoDaUserId(req.getCreatoDaUserId());
        return eventoRepo.save(e);
    }
}
