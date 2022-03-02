package com.kairosds.cursospb2.biblioteca.biblioteca.repository;

import com.kairosds.cursospb2.biblioteca.biblioteca.domain.BibliotecaLibro;
import com.kairosds.cursospb2.biblioteca.libro.domain.Libro;
import com.kairosds.cursospb2.biblioteca.libro.repository.LibroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class BibliotecaLibroRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BibliotecaLibroRepository bibliotecaLibroRepository;

    @Test
    @DisplayName("Save association bibiloteca libro")
    void saveAssociationBibliotecaLibroSuccess(){

        final var bibliotecaLibro = BibliotecaLibro.builder().bibliotecaCodigo("BIBLIMAD01")
                .libroCodigo("KF7TNED64M").build();

        final var bibliotecaLibroSaved = this.bibliotecaLibroRepository.save(bibliotecaLibro);

        Assertions.assertTrue(bibliotecaLibroSaved.getId() != null);

    }

    @Test
    @DisplayName("Invoke method existsByLibroCodigo when codigo exists")
    void WhenExistsCodigoThenExistsByCodigoMustReturnTrue(){

        final var codigo = "K84F7GNC36";

        final var bibliotecaLibro = BibliotecaLibro.builder().bibliotecaCodigo("BIBLIMAD01")
                .libroCodigo(codigo).build();

        this.entityManager.persist(bibliotecaLibro);

        final var codigoExists = this.bibliotecaLibroRepository.existsByLibroCodigo(codigo);

        Assertions.assertTrue(codigoExists);

    }

    @Test
    @DisplayName("Invoke method existsByLibroCodigo when codigo does not exist")
    void WhenNotExistsCodigoThenExistsByCodigoMustReturnFalse(){

        final var codigo = "K84F7GNC36";

        final var codigoExists = this.bibliotecaLibroRepository.existsByLibroCodigo(codigo);

        Assertions.assertFalse(codigoExists);

    }
}
