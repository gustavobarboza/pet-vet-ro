package com.gustavo.petvetro.service;

import com.gustavo.petvetro.controller.dto.UsuarioDto;
import com.gustavo.petvetro.controller.dto.UsuarioForm;
import com.gustavo.petvetro.exceptions.UsuarioDuplicadoException;
import com.gustavo.petvetro.model.Usuario;
import com.gustavo.petvetro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDto criarNovoUsuario(UsuarioForm usuarioForm){
        Usuario usuario = usuarioForm.toModel();
        if(usuarioJaExiste(usuario)){
            throw new UsuarioDuplicadoException("O usuário já existe no banco de dados.");
        }

        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuario = usuarioRepository.save(usuario);

        return new UsuarioDto(usuario);
    }

    private boolean usuarioJaExiste(Usuario usuario) {
        return usuarioRepository.existsUsuarioByUsuario(usuario.getUsuario());
    }
}
