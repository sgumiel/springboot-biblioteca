package com.kairosds.cursospb2.biblioteca.requestlibro.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestLibro implements Serializable {


    @NotNull(message = "El codigo del libro no puede ser null")
    private String libroCode;

    @NotNull(message = "El codigo de la biblioteca a la que pedir el prestamo no puede ser null")
    private String biliotecaCode;

    @NotNull(message = "Las unidades del prestamo no puede ser null")
    private Integer unidades;

    private Integer duration;
}
