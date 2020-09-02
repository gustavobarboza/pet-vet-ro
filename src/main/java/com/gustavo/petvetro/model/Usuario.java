package com.gustavo.petvetro.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Usuario {

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

}
