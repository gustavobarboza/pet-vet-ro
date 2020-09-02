package com.gustavo.petvetro.controller.dto;

import com.gustavo.petvetro.model.TipoUsuario;
import com.gustavo.petvetro.model.Usuario;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UsuarioForm {
    @NotBlank(message = "O nome de usuário é obrigatório")
    private String usuario;
    private String nome;
    @NotBlank(message = "A senha é obrigatória")
    private String senha;
    @NotBlank(message="A confirmação de senha é obrigatória")
    private String confirmacaoSenha;
    private String email;
    @NotNull
    @Pattern(regexp="ADMIN|USUARIO|CLIENTE")
    private String tipo;

    public Usuario toModel() {
        Usuario usuario = new Usuario();
        usuario.setUsuario(this.usuario);
        usuario.setNome(this.nome);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        usuario.setTipo(TipoUsuario.valueOf(this.tipo));

        return usuario;
    }
}
