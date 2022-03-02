package com.kairosds.cursospb2.biblioteca.biblioteca.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Biblioteca {

    private Long id;

    private String code;

    private Long creditos;
}
