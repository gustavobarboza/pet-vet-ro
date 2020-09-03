package com.gustavo.petvetro.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErroGenericoDto {
    private final String erro;

}
