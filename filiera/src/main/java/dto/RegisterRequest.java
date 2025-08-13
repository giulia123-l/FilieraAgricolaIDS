package dto;

import com.example.filiera.domain.Ruolo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Pattern;

public class RegisterRequest {
    @NotBlank
    public String nome;
    @NotBlank
    public String cognome;
    @Email @NotBlank
    public String email;
    @NotBlank
    public String password;
    @NotNull
    public Ruolo ruoloRichiesto;
}

