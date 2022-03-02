package com.kairosds.cursospb2.biblioteca.libro.repository;

import com.kairosds.cursospb2.biblioteca.libro.domain.Libro;
import com.kairosds.cursospb2.biblioteca.libroisbn.domain.LibroISBN;
import com.kairosds.cursospb2.biblioteca.libroisbn.repository.LibroIsbnRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class LibroRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private LibroRepository libroRepository;

    @Test
    @DisplayName("Save libro success")
    void saveLibroSuccess(){

        final var libro = Libro.builder().isbn("K84F7")
                .codigo("KF7TNED64M").build();

        final var libroSaved = this.libroRepository.save(libro);

        Assertions.assertTrue(libroSaved.getId() != null);

    }

    @Test
    @DisplayName("Invoke method existsByCodigo when codigo exists")
    void WhenExistsCodigoThenExistsByCodigoMustReturnTrue(){

        final var codigo = "K84F7GNC36";

        final var libro = Libro.builder().isbn("K84F7")
                .codigo(codigo).build();

        this.entityManager.persist(libro);

        final var codigoExists = this.libroRepository.existsByCodigo(codigo);

        Assertions.assertTrue(codigoExists);

    }

    @Test
    @DisplayName("Invoke method existsByCodigo when codigo does not exists")
    void WhenNotExistsCodigoThenExistsByCodigoMustReturnFalse(){

        final var codigo = "K84F7GNC36";

        final var codigoExists = this.libroRepository.existsByCodigo(codigo);

        Assertions.assertFalse(codigoExists);

    }
}
