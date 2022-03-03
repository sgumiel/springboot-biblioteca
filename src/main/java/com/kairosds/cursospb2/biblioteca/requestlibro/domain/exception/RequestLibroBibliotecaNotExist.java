package com.kairosds.cursospb2.biblioteca.requestlibro.domain.exception;

import lombok.Data;

@Data
public class RequestLibroBibliotecaNotExist extends RuntimeException{

    private String codigo;

    public RequestLibroBibliotecaNotExist(String codigo) {
        super("The code: " + codigo + " does not exist");
        this.codigo=codigo;
    }
}
