package com.kairosds.cursospb2.biblioteca.libroisbn.service;

import com.kairosds.cursospb2.biblioteca.config.CreditsConfiguration;
import com.kairosds.cursospb2.biblioteca.libroisbn.domain.LibroISBN;
import com.kairosds.cursospb2.biblioteca.libroisbn.repository.LibroIsbnRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LibroISbnServiceImpl implements LibroIsbnService{

    private final LibroIsbnRepository libroIsbnRepository;

    private final CreditsConfiguration creditsConfiguration;

    @Override
    public LibroISBN createLibroIsbn(LibroISBN libroIsbn) {
        return null;
    }
}
