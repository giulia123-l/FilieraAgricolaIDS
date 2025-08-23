package com.example.filiera.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @Email @NotBlank
    public String email;
    @NotBlank
    public String password;
}
