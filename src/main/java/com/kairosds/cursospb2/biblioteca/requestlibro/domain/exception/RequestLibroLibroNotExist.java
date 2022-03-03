package com.kairosds.cursospb2.biblioteca.requestlibro.domain.exception;

import lombok.Data;

@Data
public class RequestLibroLibroNotExist extends RuntimeException{

    private String codigo;

    public RequestLibroLibroNotExist(String codigo) {
        super("The code: " + codigo + " does not exist");
        this.codigo=codigo;
    }
}
