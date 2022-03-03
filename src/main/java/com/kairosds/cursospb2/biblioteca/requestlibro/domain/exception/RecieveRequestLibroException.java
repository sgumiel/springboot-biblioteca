package com.kairosds.cursospb2.biblioteca.requestlibro.domain.exception;

import lombok.Data;

@Data
public class RecieveRequestLibroException extends RuntimeException {

    private Integer statusCode;
    public RecieveRequestLibroException(Integer statusCode) {
        super("The receive request libro service responses with status code: " + statusCode);
        this.statusCode = statusCode;

    }
}
