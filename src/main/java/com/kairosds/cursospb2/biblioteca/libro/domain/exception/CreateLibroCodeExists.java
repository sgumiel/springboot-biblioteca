package com.kairosds.cursospb2.biblioteca.libro.domain.exception;

import lombok.Data;

@Data
public class CreateLibroCodeExists extends RuntimeException {

    private String codigo;

    public CreateLibroCodeExists(String codigo) {
        super("The code: " + codigo + " already exists");
        this.codigo=codigo;
    }
}
