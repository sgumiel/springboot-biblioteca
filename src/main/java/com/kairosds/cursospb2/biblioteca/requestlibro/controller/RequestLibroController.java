package com.kairosds.cursospb2.biblioteca.requestlibro.controller;

import com.kairosds.cursospb2.biblioteca.requestlibro.domain.RequestLibro;
import com.kairosds.cursospb2.biblioteca.requestlibro.service.RequestLibroService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("request-libro")
public class RequestLibroController {

    private final RequestLibroService requestLibroService;

    @PostMapping
    public ResponseEntity<Boolean> requestLibro(@RequestBody @Validated RequestLibro requestLibro) {

        final var librosPrestados = this.requestLibroService.requestLibro(requestLibro);
        return ResponseEntity.ok(librosPrestados);
    }
}
