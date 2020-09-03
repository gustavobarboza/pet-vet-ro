package com.gustavo.petvetro.security;

import com.gustavo.petvetro.model.Usuario;
import com.gustavo.petvetro.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AutenticacaoPorTokenFilter extends OncePerRequestFilter {
    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AutenticacaoPorTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);
        if(tokenService.isTokenValido(token)){
            autenticarUsuario(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarUsuario(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        UsernamePasswordAuthenticationToken autenticacao =
                new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(autenticacao);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token != null && !token.isEmpty() && token.startsWith("Bearer ")){
            return token.substring(7);
        }

        return null;
    }
}
