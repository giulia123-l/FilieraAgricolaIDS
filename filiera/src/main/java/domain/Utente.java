package domain;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "utenti", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Ruolo ruoloRichiesto;

    @Enumerated(EnumType.STRING)
    private Ruolo ruoloAssegnato;

    @Enumerated(EnumType.STRING)
    private StatoUtente stato = StatoUtente.IN_ATTESA_APPROVAZIONE;

    private String noteRifiuto;

    private OffsetDateTime createdAt = OffsetDateTime.now();
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public Ruolo getRuoloRichiesto() { return ruoloRichiesto; }
    public void setRuoloRichiesto(Ruolo ruoloRichiesto) { this.ruoloRichiesto = ruoloRichiesto; }

    public Ruolo getRuoloAssegnato() { return ruoloAssegnato; }
    public void setRuoloAssegnato(Ruolo ruoloAssegnato) { this.ruoloAssegnato = ruoloAssegnato; }

    public StatoUtente getStato() { return stato; }
    public void setStato(StatoUtente stato) { this.stato = stato; }

    public String getNoteRifiuto() { return noteRifiuto; }
    public void setNoteRifiuto(String noteRifiuto) { this.noteRifiuto = noteRifiuto; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}
