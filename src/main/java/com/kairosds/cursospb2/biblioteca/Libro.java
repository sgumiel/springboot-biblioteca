package com.kairosds.cursospb2.biblioteca;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Libro {

    private Long id;

    private String codigo;

    private String isbn;

    private Boolean prestado;
}
