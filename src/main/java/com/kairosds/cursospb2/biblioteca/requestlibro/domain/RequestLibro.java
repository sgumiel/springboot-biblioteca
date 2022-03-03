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

    @NotNull(message = "El codigo de la biblioteca no puede ser null")
    private String biliotecaCode;
}
