package com.gustavo.petvetro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsuarioDuplicadoException.class)
    public ErroGenericoDto usuarioDuplicadoHandler(UsuarioDuplicadoException ex) {
        return new ErroGenericoDto(ex.getMessage());
    }
}
