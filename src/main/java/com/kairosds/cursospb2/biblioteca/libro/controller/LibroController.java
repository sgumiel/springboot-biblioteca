package com.kairosds.cursospb2.biblioteca.libro.controller;

import com.kairosds.cursospb2.biblioteca.libro.domain.Libro;
import com.kairosds.cursospb2.biblioteca.libro.service.LibroService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("libro")
public class LibroController {

    private final LibroService libroService;

    @PostMapping
    public ResponseEntity<Libro> createLibro(@RequestBody Libro libro) {

        return ResponseEntity.ok(Libro.builder().build());
    }
}