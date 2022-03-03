package com.kairosds.cursospb2.biblioteca.biblioteca.service;

import com.kairosds.cursospb2.biblioteca.biblioteca.domain.exception.AssociateLibroBiliotecaLibroAlreadyAssociated;
import com.kairosds.cursospb2.biblioteca.biblioteca.domain.exception.AssociateLibroBiliotecaLibroCodeNotExist;
import com.kairosds.cursospb2.biblioteca.biblioteca.repository.BibliotecaLibroRepository;
import com.kairosds.cursospb2.biblioteca.config.BibliotecaDataConfiguration;
import com.kairosds.cursospb2.biblioteca.libro.repository.LibroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = BibliotecaServiceImpl.class)
public class BibliotecaServiceTest {

    @MockBean
    private BibliotecaLibroRepository bibliotecaLibroRepository;

    @MockBean
    private LibroRepository libroRepository;

    @MockBean
    private BibliotecaDataConfiguration bibliotecaDataConfiguration;

    @Autowired
    private BibliotecaService bibliotecaService;

    @Test
    @DisplayName("Associate libro when libro codigo does not exist")
    void associateLibroWhenLibroCodigoDoesBotExist() {

        final var codigo = "OK8H6NM89L";
        Mockito.when(libroRepository.existsByCodigo("OK8H6NM89L"))
                .thenReturn(false);

        Assertions.assertThrows(AssociateLibroBiliotecaLibroCodeNotExist.class, () -> {
            final var associated = this.bibliotecaService.associateLibro(codigo);
        });
    }

    @Test
    @DisplayName("Associate libro when libro is already associated")
    void associateLibroWhenLibroIsAlreadyAssociated() {

        final var codigo = "OK8H6NM89L";
        Mockito.when(libroRepository.existsByCodigo("OK8H6NM89L"))
                .thenReturn(true);

        Mockito.when(bibliotecaLibroRepository.existsByLibroCodigo("OK8H6NM89L"))
                .thenReturn(true);

        Assertions.assertThrows(AssociateLibroBiliotecaLibroAlreadyAssociated.class, () -> {
            final var associated = this.bibliotecaService.associateLibro(codigo);
        });
    }

    @Test
    @DisplayName("Associate libro success")
    void associateLibroSuccess() {

        final var codigo = "OK8H6NM89L";
        Mockito.when(libroRepository.existsByCodigo("OK8H6NM89L"))
                .thenReturn(true);

        Mockito.when(bibliotecaLibroRepository.existsByLibroCodigo("OK8H6NM89L"))
                .thenReturn(false);

        Mockito.when(bibliotecaDataConfiguration.getCodigo())
                .thenReturn("BIBLIMAD01");

        final var associated = this.bibliotecaService.associateLibro(codigo);

        Assertions.assertTrue(associated);
    }
}
