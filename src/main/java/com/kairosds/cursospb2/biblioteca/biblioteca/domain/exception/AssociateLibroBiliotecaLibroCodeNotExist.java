package com.kairosds.cursospb2.biblioteca.biblioteca.domain.exception;

import lombok.Data;

@Data
public class AssociateLibroBiliotecaLibroCodeNotExist extends RuntimeException {

    private String codigo;

    public AssociateLibroBiliotecaLibroCodeNotExist(String codigo) {
        super("The code: " + codigo + " does not exist");
        this.codigo = codigo;
    }
}
