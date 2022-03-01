package com.kairosds.cursospb2.biblioteca.libroisbn.service;

import com.kairosds.cursospb2.biblioteca.config.CreditsConfiguration;
import com.kairosds.cursospb2.biblioteca.libroisbn.domain.LibroISBN;
import com.kairosds.cursospb2.biblioteca.libroisbn.domain.exception.CreateLibroISBNCreditsMaximun;
import com.kairosds.cursospb2.biblioteca.libroisbn.domain.exception.CreateLibroISBNExists;
import com.kairosds.cursospb2.biblioteca.libroisbn.repository.LibroIsbnRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = LibroISbnServiceImpl.class)
public class LibroIsbnServiceTest {

    @MockBean
    private LibroIsbnRepository libroIsbnRepository;

    @MockBean
    private CreditsConfiguration creditsConfiguration;

    @Autowired
    private LibroIsbnService libroIsbnService;

    @Test
    @DisplayName("Create libro isbn when isbn exists")
    void createLibroIsbnWhenIsbnExists() {

        Mockito.when(libroIsbnRepository.existsByIsbn(ArgumentMatchers.anyString()))
                .thenReturn(true);

        final var libroIsbn = LibroISBN.builder()
                .isbn("A45HY8J3DC").titulo("La reina roja").autor("Posteguillo").creditos(12).build();

        Assertions.assertThrows(CreateLibroISBNExists.class, () -> {
            this.libroIsbnService.createLibroIsbn(libroIsbn);
        });
    }

    @Test
    @DisplayName("Create libro isbn when credits are greather than maximun")
    void createLibroIsbnWhenCreditsIsGreatherThanMAximun() {

        final var maxPerlibro = 15;
        final var creditsNewLibro = 100;

        Mockito.when(libroIsbnRepository.existsByIsbn(ArgumentMatchers.anyString()))
                .thenReturn(false);

        Mockito.when(creditsConfiguration.getMaxPerLibro())
                .thenReturn(maxPerlibro);

        final var libroIsbn = LibroISBN.builder()
                .isbn("A45HY8J3DC").titulo("La reina roja").autor("Posteguillo").creditos(creditsNewLibro).build();

        Assertions.assertThrows(CreateLibroISBNCreditsMaximun.class, () -> {
            this.libroIsbnService.createLibroIsbn(libroIsbn);
        });
    }

    @Test
    @DisplayName("Create libro isbn success")
    void createLibroIsbnSuccess() {

        final var maxPerlibro = 15;
        final var creditsNewLibro = 7;

        final var libroIsbnSaved = LibroISBN.builder()
                .id(1L)
                .isbn("A45HY8J3DC").titulo("La reina roja").autor("Posteguillo").creditos(creditsNewLibro).build();

        Mockito.when(libroIsbnRepository.existsByIsbn(ArgumentMatchers.anyString()))
                .thenReturn(false);

        Mockito.when(creditsConfiguration.getMaxPerLibro())
                .thenReturn(maxPerlibro);

        Mockito.when(libroIsbnRepository.save(ArgumentMatchers.any(LibroISBN.class)))
                .thenReturn(libroIsbnSaved);

        final var libroIsbn = LibroISBN.builder()
                .isbn("A45HY8J3DC").titulo("La reina roja").autor("Posteguillo").creditos(creditsNewLibro).build();

        final var libroIsbnCreated = this.libroIsbnService.createLibroIsbn(libroIsbn);

        Assertions.assertEquals(libroIsbnCreated.getId().longValue(),libroIsbnSaved.getId().longValue());

    }
}
