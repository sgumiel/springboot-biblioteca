package com.kairosds.cursospb2.biblioteca.libro.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "LIBRO")
@AllArgsConstructor
@NoArgsConstructor
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String codigo;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "status")
    private Boolean status;
}
