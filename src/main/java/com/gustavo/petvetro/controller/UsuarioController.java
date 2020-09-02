package com.gustavo.petvetro.controller;

import com.gustavo.petvetro.controller.dto.UsuarioDto;
import com.gustavo.petvetro.controller.dto.UsuarioForm;
import com.gustavo.petvetro.model.Usuario;
import com.gustavo.petvetro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    ResponseEntity<UsuarioDto> criarUsuario(@Valid @RequestBody UsuarioForm usuarioForm, UriComponentsBuilder builder) {
        Usuario usuario = usuarioForm.toModel();
        usuario = usuarioRepository.save(usuario);

        return ResponseEntity.created(builder.path("usuario/{id}")
        .buildAndExpand(usuario.getId()).toUri())
                .body(new UsuarioDto(usuario));
    }
}
