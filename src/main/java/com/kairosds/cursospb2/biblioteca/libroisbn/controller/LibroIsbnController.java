package com.kairosds.cursospb2.biblioteca.libroisbn.controller;

import com.kairosds.cursospb2.biblioteca.libroisbn.domain.LibroISBN;
import com.kairosds.cursospb2.biblioteca.libroisbn.service.LibroIsbnService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("libro-isbn")
public class LibroIsbnController {

    private final LibroIsbnService libroIsbnService;

    @PutMapping
    public ResponseEntity<LibroISBN> createLibroIsbn(@RequestBody LibroISBN libroIsbn) {

        final var libroIsbnCreated = this.libroIsbnService.createLibroIsbn(libroIsbn);

        return ResponseEntity.ok(libroIsbnCreated);
    }
}