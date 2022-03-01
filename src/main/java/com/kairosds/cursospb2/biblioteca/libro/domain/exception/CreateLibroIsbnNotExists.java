package com.kairosds.cursospb2.biblioteca.libro.domain.exception;

import lombok.Data;

@Data
public class CreateLibroIsbnNotExists extends RuntimeException {

    private String isbn;

    public CreateLibroIsbnNotExists(String isbn) {
        super("The isbn: " + isbn + " not exists");
        this.isbn=isbn;
    }
}
