package com.gustavo.petvetro.service;

import com.gustavo.petvetro.model.Usuario;
import com.gustavo.petvetro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public AutenticacaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findUsuarioByUsuario(username);
        if(usuarioOptional.isPresent()){
            return usuarioOptional.get();
        } else {
            throw new UsernameNotFoundException("Nenhum usuário encontrado com os dados fornecidos.");
        }
    }
}
