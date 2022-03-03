package com.kairosds.cursospb2.biblioteca.prestamo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Builder
@Entity
@Table(name = "PRESTAMO")
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "biblioteca1")  // quien quiere el prestamo
    private String biblioteca1;

    @Column(name = "biblioteca2") // biblioteca que presta
    private String biblioteca2;

    @Column(name = "librocode")
    private String librocode;

    @Column(name = "lenddate")
    private OffsetDateTime lenddate;

    @Column(name = "returndate")
    private OffsetDateTime returndate;
}
