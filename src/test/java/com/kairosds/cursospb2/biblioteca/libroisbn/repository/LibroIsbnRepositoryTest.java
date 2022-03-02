package com.kairosds.cursospb2.biblioteca.libroisbn.repository;

import com.kairosds.cursospb2.biblioteca.libroisbn.domain.LibroISBN;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class LibroIsbnRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LibroIsbnRepository libroIsbnRepository;

    @Test
    @DisplayName("Save libro isbn")
    void saveLibroIsbn(){

        final var libroIsbn = LibroISBN.builder().isbn("K84F7GNC36")
                .titulo("titulo del libro").autor("Autor1").creditos(10).build();

        final var libroIsbnSaved = this.libroIsbnRepository.save(libroIsbn);

        Assertions.assertTrue(libroIsbnSaved.getId() != null);

    }

    @Test
    @DisplayName("Invoke method existsByIsbn when isbn exists")
    void WhenExistsIsbnThenExistsByIsbnMustReturnTrue(){

        final var isbn = "K84F7GNC36";

        final var libroIsbn = LibroISBN.builder().isbn(isbn)
                .titulo("titulo del libro").autor("Autor1").creditos(10).build();

        this.entityManager.persist(libroIsbn);

        final var isbnExists = this.libroIsbnRepository.existsByIsbn(isbn);

        Assertions.assertTrue(isbnExists);

    }

    @Test
    @DisplayName("Invoke method existsByIsbn when isbn does not exists")
    void WhenNotExistsIsbnThenExistsByIsbnMustReturnFalse(){

        final var isbn = "K84F7GNC36";

        final var isbnExists = this.libroIsbnRepository.existsByIsbn(isbn);

        Assertions.assertFalse(isbnExists);

    }
}
