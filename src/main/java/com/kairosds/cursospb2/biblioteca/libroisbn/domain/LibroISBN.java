package com.kairosds.cursospb2.biblioteca.libroisbn.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "LIBRO_ISBN")
@AllArgsConstructor
@NoArgsConstructor
public class LibroISBN {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "credits")
    private Integer creditos;

    @Column(name = "title")
    private String titulo;

    @Column(name = "author")
    private String autor;
}
