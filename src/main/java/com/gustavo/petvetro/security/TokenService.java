package com.gustavo.petvetro.security;

import com.gustavo.petvetro.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Value("${petvetro.jwt.expiration}")
    private String expiration;
    @Value("${petvetro.jwt.secret}")
    private String secret;

    private static final String ISSUER = "Pet Vet Ro Spring EndPoint";

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret)
                .parseClaimsJws(token).getBody();

        return Long.parseLong(claims.getSubject());
    }

    public String gerarToken(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Date now = new Date();
        Date expire = new Date(now.getTime() + Long.valueOf(expiration));

        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(usuario.getId().toString())
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
