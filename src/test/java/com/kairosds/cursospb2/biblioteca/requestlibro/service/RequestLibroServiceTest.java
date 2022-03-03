package com.kairosds.cursospb2.biblioteca.requestlibro.service;

import com.kairosds.cursospb2.biblioteca.config.BibliotecaServicesConfiguration;
import com.kairosds.cursospb2.biblioteca.libro.repository.LibroRepository;
import com.kairosds.cursospb2.biblioteca.requestlibro.client.ReceiveRequestLibroClient;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.RequestLibro;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.exception.RequestLibroBibliotecaNotExist;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.exception.RequestLibroLibroNotExist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = RequestLibroServiceImpl.class)
public class RequestLibroServiceTest {

    @Autowired
    private RequestLibroService requestLibroService;

    @MockBean
    private ReceiveRequestLibroClient receiveRequestLibroClient;

    @MockBean
    private LibroRepository libroRepository;

    @MockBean
    private BibliotecaServicesConfiguration bibliotecaServicesConfiguration;

    @Test
    @DisplayName("Request Libro success with true")
    void requestLibroSuccessTrue() {

        final var libroCode = "A47HUR7J65";
        final var bibliotecaCode = "L9D5FBVC4R";

        final var requestLibro = RequestLibro.builder().libroCode(libroCode)
                .biliotecaCode(bibliotecaCode).unidades(2).build();

        Mockito.when(this.libroRepository.existsByCodigo(libroCode))
                .thenReturn(true);

        Mockito.when(this.bibliotecaServicesConfiguration.isBibliotecaConfigured(bibliotecaCode))
                .thenReturn(true);

        Mockito.when(this.receiveRequestLibroClient.requestLibro(requestLibro))
                .thenReturn(true);

        final var requestLibroResponse = this.requestLibroService.requestLibro(requestLibro);

        Assertions.assertTrue(requestLibroResponse);
    }

    @Test
    @DisplayName("Request Libro success with false")
    void requestLibroSuccessFalse() {

        final var libroCode = "A47HUR7J65";
        final var bibliotecaCode = "L9D5FBVC4R";

        final var requestLibro = RequestLibro.builder().libroCode(libroCode)
                .biliotecaCode(bibliotecaCode).unidades(2).build();

        Mockito.when(this.libroRepository.existsByCodigo(libroCode))
                .thenReturn(true);

        Mockito.when(this.bibliotecaServicesConfiguration.isBibliotecaConfigured(bibliotecaCode))
                .thenReturn(true);

        Mockito.when(this.receiveRequestLibroClient.requestLibro(requestLibro))
                .thenReturn(false);

        final var requestLibroResponse = this.requestLibroService.requestLibro(requestLibro);

        Assertions.assertFalse(requestLibroResponse);
    }

    @Test
    @DisplayName("Request Libro when libro not exists")
    void requestLibroWhenLibroNotExists() {

        final var libroCode = "A47HUR7J65";
        final var bibliotecaCode = "L9D5FBVC4R";

        final var requestLibro = RequestLibro.builder().libroCode(libroCode)
                .biliotecaCode(bibliotecaCode).unidades(2).build();

        Mockito.when(this.libroRepository.existsByCodigo(libroCode))
                .thenReturn(false);

        Mockito.when(this.bibliotecaServicesConfiguration.isBibliotecaConfigured(bibliotecaCode))
                .thenReturn(true);

        Mockito.when(this.receiveRequestLibroClient.requestLibro(requestLibro))
                .thenReturn(false);

        Assertions.assertThrows(RequestLibroLibroNotExist.class, () -> {
            final var requestLibroResponse = this.requestLibroService.requestLibro(requestLibro);
        });
    }

    @Test
    @DisplayName("Request Libro when biblioteca not exists")
    void requestLibroWhenBibliotecaNotExists() {

        final var libroCode = "A47HUR7J65";
        final var bibliotecaCode = "L9D5FBVC4R";

        final var requestLibro = RequestLibro.builder().libroCode(libroCode)
                .biliotecaCode(bibliotecaCode).unidades(2).build();

        Mockito.when(this.libroRepository.existsByCodigo(libroCode))
                .thenReturn(true);

        Mockito.when(this.bibliotecaServicesConfiguration.isBibliotecaConfigured(bibliotecaCode))
                .thenReturn(false);

        Mockito.when(this.receiveRequestLibroClient.requestLibro(requestLibro))
                .thenReturn(false);

        Assertions.assertThrows(RequestLibroBibliotecaNotExist.class, () -> {
            final var requestLibroResponse = this.requestLibroService.requestLibro(requestLibro);
        });
    }
}
