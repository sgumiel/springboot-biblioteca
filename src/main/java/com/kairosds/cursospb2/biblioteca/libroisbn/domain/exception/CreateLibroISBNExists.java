package com.kairosds.cursospb2.biblioteca.libroisbn.domain.exception;

import lombok.Data;

@Data
public class CreateLibroISBNExists extends RuntimeException {

    private String isbn;

    public CreateLibroISBNExists(String isbn) {
        super("The isbn: " + isbn + " already exists");
        this.isbn=isbn;
    }
}
