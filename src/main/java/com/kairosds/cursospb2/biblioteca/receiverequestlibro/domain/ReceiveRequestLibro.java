package com.kairosds.cursospb2.biblioteca.receiverequestlibro.domain;

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
public class ReceiveRequestLibro implements Serializable {

    @NotNull(message = "El codigo del libro no puede ser null")
    private String libroCode;

    @NotNull(message = "El codigo de la biblioteca que quiere el prestamo no puede ser null")
    private String biliotecaCode;

    @NotNull(message = "Las unidades de libros pedidos no puede ser null")
    private Integer unidades;

    private Integer periodo;
}
