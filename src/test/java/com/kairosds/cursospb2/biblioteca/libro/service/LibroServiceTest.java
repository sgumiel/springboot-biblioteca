package com.kairosds.cursospb2.biblioteca.libro.service;

import com.kairosds.cursospb2.biblioteca.libro.domain.Libro;
import com.kairosds.cursospb2.biblioteca.libro.domain.exception.CreateLibroCodeExists;
import com.kairosds.cursospb2.biblioteca.libro.domain.exception.CreateLibroIsbnNotExists;
import com.kairosds.cursospb2.biblioteca.libro.repository.LibroRepository;
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
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = LibroServiceImpl.class)
public class LibroServiceTest {

    @MockBean
    private LibroIsbnRepository libroIsbnRepository;

    @MockBean
    private LibroRepository libroRepository;

    @Autowired
    private LibroService libroService;

    @Test
    @DisplayName("Create libro when isbn does not exist")
    void createLibroWhenIsbnDoestNotExist() {

        Mockito.when(libroIsbnRepository.existsByIsbn(ArgumentMatchers.anyString()))
                .thenReturn(false);

        final var libro = Libro.builder()
                .isbn("A45YC").codigo("OL7VFH53D5").build();

        Assertions.assertThrows(CreateLibroIsbnNotExists.class, () -> {
            this.libroService.createLibro(libro);
        });
    }

    @Test
    @DisplayName("Create libro when codigo exists")
    void createLibroWhenCodigoExists() {

        Mockito.when(libroIsbnRepository.existsByIsbn(ArgumentMatchers.anyString()))
                .thenReturn(true);

        Mockito.when(libroRepository.existsByCodigo(ArgumentMatchers.anyString()))
                .thenReturn(true);

        final var libro = Libro.builder()
                .isbn("A45YC").codigo("OL7VFH53D5").build();

        Assertions.assertThrows(CreateLibroCodeExists.class, () -> {
            this.libroService.createLibro(libro);
        });
    }

    @Test
    @DisplayName("Create libro success")
    void createLibroSuccess() {

        final var libroSaved = Libro.builder()
                .id(1L).isbn("A45YC").codigo("OL7VFH53D5").build();

        Mockito.when(libroIsbnRepository.existsByIsbn(ArgumentMatchers.anyString()))
                .thenReturn(true);

        Mockito.when(libroRepository.existsByCodigo(ArgumentMatchers.anyString()))
                .thenReturn(false);

        Mockito.when(libroRepository.save(ArgumentMatchers.any(Libro.class)))
                .thenReturn(libroSaved);

        final var libro = Libro.builder()
                .isbn("A45YC").codigo("OL7VFH53D5").build();

        final var libroCreated = this.libroService.createLibro(libro);

        Assertions.assertEquals(libroCreated.getId(),libroSaved.getId());

    }
}
