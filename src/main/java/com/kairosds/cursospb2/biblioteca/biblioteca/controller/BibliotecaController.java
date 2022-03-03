package com.kairosds.cursospb2.biblioteca.biblioteca.controller;

import com.kairosds.cursospb2.biblioteca.biblioteca.service.BibliotecaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("biblioteca")
public class BibliotecaController {

    private BibliotecaService bibliotecaService;

    @PostMapping("/{libroCode}")
    public ResponseEntity<Boolean> associateBibliotecaLibro(@PathVariable("libroCode") String code) {

        final var associated = bibliotecaService.associateLibro(code);
        return ResponseEntity.ok(associated);
    }

}
