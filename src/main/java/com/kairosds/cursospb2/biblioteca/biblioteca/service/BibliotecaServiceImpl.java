package com.kairosds.cursospb2.biblioteca.biblioteca.service;

import com.kairosds.cursospb2.biblioteca.biblioteca.domain.BibliotecaLibro;
import com.kairosds.cursospb2.biblioteca.biblioteca.domain.exception.AssociateLibroBiliotecaLibroAlreadyAssociated;
import com.kairosds.cursospb2.biblioteca.biblioteca.domain.exception.AssociateLibroBiliotecaLibroCodeNotExist;
import com.kairosds.cursospb2.biblioteca.biblioteca.repository.BibliotecaLibroRepository;
import com.kairosds.cursospb2.biblioteca.config.BibliotecaConfiguration;
import com.kairosds.cursospb2.biblioteca.libro.repository.LibroRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BibliotecaServiceImpl implements BibliotecaService {

    @Autowired
    private final LibroRepository libroRepository;

    @Autowired
    private final BibliotecaLibroRepository bibliotecaLibroRepository;

    @Autowired
    private BibliotecaConfiguration bibliotecaConfiguration;

    @Override
    public Boolean associateLibro(String codigo) {

        final var libroCodigoExists = this.libroRepository.existsByCodigo(codigo);

        if (!libroCodigoExists) {
            throw new AssociateLibroBiliotecaLibroCodeNotExist(codigo);
        }

        final var libroAlreadyAssociated = this.bibliotecaLibroRepository.existsByLibroCodigo(codigo);
        if (libroAlreadyAssociated) {
            throw new AssociateLibroBiliotecaLibroAlreadyAssociated(codigo);
        }

        final var bibliotecaCode = this.bibliotecaConfiguration.getCodigo();

        final var bibliotecaLibro = BibliotecaLibro.builder()
                .bibliotecaCodigo(bibliotecaCode)
                .libroCodigo(codigo).build();

        final var bibliotecaLibroSaved = this.bibliotecaLibroRepository.save(bibliotecaLibro);
        return true;
    }
}
