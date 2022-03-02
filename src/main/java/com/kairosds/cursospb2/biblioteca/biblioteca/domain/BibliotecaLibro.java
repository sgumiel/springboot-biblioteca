package com.kairosds.cursospb2.biblioteca.biblioteca.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "BIBLIOTECA_LIBRO")
@AllArgsConstructor
@NoArgsConstructor
public class BibliotecaLibro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "biblioteca_code")
    private String bibliotecaCodigo;

    @Column(name = "libro_code")
    private String libroCodigo;
}
