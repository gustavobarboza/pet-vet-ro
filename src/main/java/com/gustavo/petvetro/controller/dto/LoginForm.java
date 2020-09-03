package com.gustavo.petvetro.controller.dto;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {
    @NotBlank(message = "O usuário não pode estar vazio")
    private String usuario;
    @NotBlank(message = "A senha não pode estar vazia")
    private String senha;

    public UsernamePasswordAuthenticationToken toAuthenticationToken(){
        return new UsernamePasswordAuthenticationToken(this.usuario, this.senha);
    }
}
