package com.gustavo.petvetro.controller.dto;

import com.gustavo.petvetro.model.Usuario;
import lombok.Data;

@Data
public class UsuarioDto {
    private Long id;
    private String usuario;
    private String nome;
    private String email;

    public UsuarioDto() {
    }

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.usuario = usuario.getUsuario();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }
}
