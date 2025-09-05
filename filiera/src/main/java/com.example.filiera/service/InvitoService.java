package com.example.filiera.service;

import com.example.filiera.domain.Evento;
import com.example.filiera.domain.InvitoEvento;
import com.example.filiera.domain.Utente;
import com.example.filiera.dto.CreateInvitiRequest;
import com.example.filiera.factory.InvitoFactory;
import com.example.filiera.repository.EventoRepository;
import com.example.filiera.repository.InvitoEventoRepository;
import com.example.filiera.repository.UserRepository;
import com.example.filiera.security.Action;
import com.example.filiera.security.PermissionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvitoService {

    private final EventoRepository eventoRepo;
    private final UserRepository userRepo;
    private final InvitoEventoRepository invitoRepo;
    private final PermissionService permissionService = new PermissionService();
    private final InvitoFactory factory = new InvitoFactory();

    public InvitoService(EventoRepository eventoRepo, UserRepository userRepo, InvitoEventoRepository invitoRepo) {
        this.eventoRepo = eventoRepo;
        this.userRepo = userRepo;
        this.invitoRepo = invitoRepo;
    }

    @Transactional
    public List<InvitoEvento> invita(CreateInvitiRequest req) {
        Utente animatore = userRepo.findById(req.getAnimatoreId()).orElseThrow(() -> new EntityNotFoundException("Animatore non trovato"));
        permissionService.strategyFor(Action.INVITE_EVENT).check(animatore);
        Evento evento = eventoRepo.findById(req.getEventoId()).orElseThrow(() -> new EntityNotFoundException("Evento non trovato"));

        List<InvitoEvento> out = new ArrayList<>();
        for (Long invitedId : req.getInvitatiUserIds()) {
            userRepo.findById(invitedId).orElseThrow(() -> new EntityNotFoundException("Utente invitato non trovato: " + invitedId));
            InvitoEvento inv = factory.create(evento, invitedId, animatore.getId());
            out.add(inv);
        }
        return invitoRepo.saveAll(out);
    }
}
