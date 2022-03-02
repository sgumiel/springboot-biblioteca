package com.kairosds.cursospb2.biblioteca.biblioteca.controller;

import com.kairosds.cursospb2.biblioteca.biblioteca.service.BibliotecaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("biblioteca")
public class BibliotecaController {

    private BibliotecaService bibliotecaService;

    @PostMapping("/{libroCode}")
    public ResponseEntity<Boolean> associateBibliotecaLibro(@PathVariable("libroCode") String code) {

        final var associated = bibliotecaService.associateLibro(code);
        return ResponseEntity.ok(false);
    }
}
