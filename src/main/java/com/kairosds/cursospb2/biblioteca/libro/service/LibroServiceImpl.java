package com.kairosds.cursospb2.biblioteca.libro.service;

import com.kairosds.cursospb2.biblioteca.libro.domain.Libro;
import com.kairosds.cursospb2.biblioteca.libro.domain.exception.CreateLibroCodeExists;
import com.kairosds.cursospb2.biblioteca.libro.domain.exception.CreateLibroIsbnNotExists;
import com.kairosds.cursospb2.biblioteca.libro.repository.LibroRepository;
import com.kairosds.cursospb2.biblioteca.libroisbn.repository.LibroIsbnRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LibroServiceImpl implements LibroService{

    private final LibroRepository libroRepository;

    private final LibroIsbnRepository libroIsbnRepository;

    @Override
    public Libro createLibro(Libro libro) {

        final var isbn = libro.getIsbn();
        final var isbnExists = this.libroIsbnRepository.existsByIsbn(isbn);

        if(!isbnExists) {
            throw new CreateLibroIsbnNotExists(isbn);
        }

        final var codigo = libro.getCodigo();
        final var codigoExists = this.libroRepository.existsByCodigo(codigo);

        if(codigoExists) {
            throw new CreateLibroCodeExists(codigo);
        }

        final var libroSaved = this.libroRepository.save(libro);
        return libroSaved;
    }
}
