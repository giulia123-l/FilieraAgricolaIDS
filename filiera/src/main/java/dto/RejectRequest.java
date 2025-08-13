package dto;

import jakarta.validation.constraints.NotBlank;

public class RejectRequest {
    @NotBlank
    public String motivazione;
}
