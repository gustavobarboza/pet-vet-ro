package com.gustavo.petvetro.exceptions;

public class UsuarioDuplicadoException extends RuntimeException{
    public UsuarioDuplicadoException() {
    }
    public UsuarioDuplicadoException(String message) {
        super(message);
    }
}
