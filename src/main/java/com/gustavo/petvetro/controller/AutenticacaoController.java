package com.gustavo.petvetro.controller;

import com.gustavo.petvetro.controller.dto.LoginDto;
import com.gustavo.petvetro.controller.dto.LoginForm;
import com.gustavo.petvetro.model.Usuario;
import com.gustavo.petvetro.repository.UsuarioRepository;
import com.gustavo.petvetro.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AutenticacaoController(AuthenticationManager authenticationManager,
                                  TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<LoginDto> autenticarUsuario(@Valid @RequestBody LoginForm loginForm) {
        UsernamePasswordAuthenticationToken usuarioAuthentication = loginForm.toAuthenticationToken();

        try {
            Authentication authentication = authenticationManager.authenticate(usuarioAuthentication);
            String token = tokenService.gerarToken(authentication);
            Usuario usuario = usuarioRepository.findUsuarioByUsuario(loginForm.getUsuario()).get();

            return ResponseEntity.ok(new LoginDto(token, usuario));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
