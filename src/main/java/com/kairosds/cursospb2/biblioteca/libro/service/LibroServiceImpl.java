package com.kairosds.cursospb2.biblioteca.libro.service;

import com.kairosds.cursospb2.biblioteca.libro.domain.Libro;
import com.kairosds.cursospb2.biblioteca.libro.repository.LibroRepository;
import com.kairosds.cursospb2.biblioteca.libroisbn.repository.LibroIsbnRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LibroServiceImpl implements LibroService{

    private final LibroIsbnRepository libroIsbnRepository;

    private final LibroRepository libroRepository;

    @Override
    public Libro createLibro(Libro libro) {
        return Libro.builder().build();
    }
}
