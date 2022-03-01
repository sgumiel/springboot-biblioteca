package com.kairosds.cursospb2.biblioteca.libro.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kairosds.cursospb2.biblioteca.apierror.ApiError;
import com.kairosds.cursospb2.biblioteca.apierror.CreateLibroIsbnErrors;
import com.kairosds.cursospb2.biblioteca.libro.domain.Libro;
import com.kairosds.cursospb2.biblioteca.libro.domain.error.CreateLibroErrors;
import com.kairosds.cursospb2.biblioteca.libro.domain.exception.CreateLibroCodeExists;
import com.kairosds.cursospb2.biblioteca.libro.domain.exception.CreateLibroIsbnNotExists;
import com.kairosds.cursospb2.biblioteca.libro.service.LibroService;
import com.kairosds.cursospb2.biblioteca.libroisbn.domain.LibroISBN;
import com.kairosds.cursospb2.biblioteca.libroisbn.domain.exception.CreateLibroISBNCreditsMaximun;
import com.kairosds.cursospb2.biblioteca.libroisbn.domain.exception.CreateLibroISBNExists;
import com.kairosds.cursospb2.biblioteca.libroisbn.service.LibroIsbnService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = LibroController.class)
public class LibroControllerTest {

    private static final String LIBRO_PATH = "/libro";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LibroService libroService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Create Libro when isbn not exists")
    void createLibroWhenIsbnNotExists() throws Exception {

        final var isbn = "ABCDEFG123";

        Mockito.when(libroService.createLibro(ArgumentMatchers.any(Libro.class)))
                .thenThrow(new CreateLibroIsbnNotExists(isbn));

        final var libro = Libro.builder()
                .isbn(isbn).codigo("S2B7HQRWW8").build();

        final var libroStringJson = this.objectMapper.writeValueAsString(libro);

        final var resultString = mockMvc
                .perform(MockMvcRequestBuilders.post(LIBRO_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(libroStringJson))
                .andReturn().getResponse().getContentAsString();

        final var createLibroIsbnNotExists = this.objectMapper.readValue(resultString, new TypeReference<ApiError>() {});

        Assertions.assertThat(createLibroIsbnNotExists.getCode()).isEqualTo(CreateLibroErrors.ISBN_NOT_EXISTS.getCode());

    }

    @Test
    @DisplayName("Create Libro when code exists exists")
    void createLibroWhenCodeExists() throws Exception {

        final var codigo = "ABCDE";

        Mockito.when(libroService.createLibro(ArgumentMatchers.any(Libro.class)))
                .thenThrow(new CreateLibroCodeExists(codigo));

        final var libro = Libro.builder()
                .isbn("ABCDEFG123").codigo(codigo).build();

        final var libroStringJson = this.objectMapper.writeValueAsString(libro);

        final var resultString = mockMvc
                .perform(MockMvcRequestBuilders.post(LIBRO_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(libroStringJson))
                .andReturn().getResponse().getContentAsString();

        final var createLibroIsbnNotExists = this.objectMapper.readValue(resultString, new TypeReference<ApiError>() {});

        Assertions.assertThat(createLibroIsbnNotExists.getCode()).isEqualTo(CreateLibroErrors.CODE_EXISTS.getCode());

    }

    @Test
    @DisplayName("Create Libro success")
    void createLibroSuccess() throws Exception {

        final var libroCreated = Libro.builder()
                .id(1L).isbn("ABCDEFG123").codigo("ABCDE").build();

        Mockito.when(libroService.createLibro(ArgumentMatchers.any(Libro.class)))
                .thenReturn(libroCreated);

        final var libroRequest = Libro.builder()
                .isbn("ABCDEFG123").codigo("ABCDE").build();

        final var libroStringJson = this.objectMapper.writeValueAsString(libroRequest);

        final var resultString = mockMvc
                .perform(MockMvcRequestBuilders.post(LIBRO_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(libroStringJson))
                .andReturn().getResponse().getContentAsString();

        final var libroResponse = this.objectMapper.readValue(resultString, new TypeReference<Libro>() {});

        Assertions.assertThat(libroResponse.getId()).isEqualTo(libroCreated.getId());

    }
}
