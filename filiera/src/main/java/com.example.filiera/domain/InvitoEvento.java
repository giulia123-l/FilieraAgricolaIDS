package com.example.filiera.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "inviti_evento")
public class InvitoEvento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @Column(nullable=false)
    private Long invitatoUserId;

    @Column(nullable=false)
    private Long inviatoDaUserId;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private StatoInvito stato = StatoInvito.INVIATO;

    public Long getId() { return id; }
    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }
    public Long getInvitatoUserId() { return invitatoUserId; }
    public void setInvitatoUserId(Long invitatoUserId) { this.invitatoUserId = invitatoUserId; }
    public Long getInviatoDaUserId() { return inviatoDaUserId; }
    public void setInviatoDaUserId(Long inviatoDaUserId) { this.inviatoDaUserId = inviatoDaUserId; }
    public StatoInvito getStato() { return stato; }
    public void setStato(StatoInvito stato) { this.stato = stato; }
}
