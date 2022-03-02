package com.kairosds.cursospb2.biblioteca.biblioteca.domain.exception;

import lombok.Data;

@Data
public class AssociateLibroBiliotecaLibroAlreadyAssociated extends RuntimeException {

    private String codigo;

    public AssociateLibroBiliotecaLibroAlreadyAssociated(String codigo) {
        super("The libro with code: " + codigo + " is already associated");
        this.codigo = codigo;
    }
}
