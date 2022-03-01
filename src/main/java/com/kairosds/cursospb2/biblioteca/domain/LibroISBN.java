package com.kairosds.cursospb2.biblioteca.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LibroISBN {

    private Long id;

    private String isbn;

    private Integer creditos;

    private String titulo;

    private String autor;
}
