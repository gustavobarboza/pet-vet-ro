package com.gustavo.petvetro.controller.dto;

import com.gustavo.petvetro.model.Usuario;
import lombok.Data;

import java.util.Optional;

@Data
public class LoginDto {
    private String token;
    private Long id;
    private String usuario;
    private String email;

    public LoginDto(String token, Usuario usuario) {
        this.token = token;
        this.id = usuario.getId();
        this.usuario = usuario.getUsuario();
        this.email = usuario.getEmail();

    }
}
