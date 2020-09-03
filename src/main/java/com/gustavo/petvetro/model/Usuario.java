package com.gustavo.petvetro.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="usuario", unique = true, nullable = false)
    private String usuario;
    @Column(name="senha", nullable = false)
    private String senha;
    @Column(name="nome", nullable = false)
    private String nome;
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name="tipo", nullable = false)
    private TipoUsuario tipo;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Autoridade> autoridades = new ArrayList<>();

    @Override
    public Collection<Autoridade> getAuthorities() {
        return this.autoridades;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
