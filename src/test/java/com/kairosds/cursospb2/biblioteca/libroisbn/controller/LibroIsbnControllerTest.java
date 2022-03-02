package com.kairosds.cursospb2.biblioteca.libroisbn.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kairosds.cursospb2.biblioteca.apierror.ApiError;
import com.kairosds.cursospb2.biblioteca.libroisbn.domain.error.CreateLibroIsbnErrors;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles("test")
@WebMvcTest(controllers = LibroIsbnController.class)
public class LibroIsbnControllerTest {

    private static final String LIBRO_ISBN_PATH = "/libro-isbn";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LibroIsbnService libroIsbnService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Create Libro ISBN when isbn exists")
    void createLibroIsbnWhenIsbnExists() throws Exception {

        final var isbn = "ABCDEFG123";

        Mockito.when(libroIsbnService.createLibroIsbn(ArgumentMatchers.any(LibroISBN.class)))
                .thenThrow(new CreateLibroISBNExists(isbn));

        final var libroIsbn = LibroISBN.builder()
                .isbn(isbn).titulo("La reina roja").autor("Posteguillo").creditos(12).build();

        final var libroIsbnStringJson = this.objectMapper.writeValueAsString(libroIsbn);

        final var resultString = mockMvc
                .perform(MockMvcRequestBuilders.put(LIBRO_ISBN_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(libroIsbnStringJson))
                .andReturn().getResponse().getContentAsString();

        final var createLibroISBNExist = this.objectMapper.readValue(resultString, new TypeReference<ApiError>() {});

        Assertions.assertThat(createLibroISBNExist.getCode()).isEqualTo(CreateLibroIsbnErrors.ISBN_EXISTS.getCode());

    }

    @Test
    @DisplayName("Create Libro ISBN when credits is greather than the maximun")
    void createLibroIsbnWhenCreditsIsGreatherThanMaximun() throws Exception {

        final var credits = 10;

        Mockito.when(libroIsbnService.createLibroIsbn(ArgumentMatchers.any(LibroISBN.class)))
                .thenThrow(new CreateLibroISBNCreditsMaximun(credits, 5));

        final var libroIsbn = LibroISBN.builder()
                .isbn("ABCDEFG123").titulo("La reina roja").autor("Posteguillo").creditos(credits).build();

        final var libroIsbnStringJson = this.objectMapper.writeValueAsString(libroIsbn);

        final var resultString = mockMvc
                .perform(MockMvcRequestBuilders.put(LIBRO_ISBN_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(libroIsbnStringJson))
                .andReturn().getResponse().getContentAsString();

        final var createLibroISBNExist = this.objectMapper.readValue(resultString, new TypeReference<ApiError>() {});

        Assertions.assertThat(createLibroISBNExist.getCode()).isEqualTo(CreateLibroIsbnErrors.CREDITS_MAX_REACHED.getCode());

    }

    @Test
    @DisplayName("Create Libro ISBN success")
    void createLibroIsbnsuccess() throws Exception {

        final var libroIsbnS = LibroISBN.builder()
                .id(1L).isbn("AGBH673HU9").titulo("La reina roja").autor("Posteguillo").creditos(12).build();

        Mockito.when(libroIsbnService.createLibroIsbn(ArgumentMatchers.any(LibroISBN.class)))
                .thenReturn(libroIsbnS);

        final var libroIsbnRequest = LibroISBN.builder()
                .isbn("AGBH673HU9").titulo("La reina roja").autor("Posteguillo").creditos(12).build();

        final var libroIsbnStringJson = this.objectMapper.writeValueAsString(libroIsbnRequest);

        final var resultString = mockMvc
                .perform(MockMvcRequestBuilders.put(LIBRO_ISBN_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(libroIsbnStringJson))
                .andReturn().getResponse().getContentAsString();

        final var responseLibroIsbn = this.objectMapper.readValue(resultString, new TypeReference<LibroISBN>() {});

        Assertions.assertThat(responseLibroIsbn.getId().longValue()).isEqualTo(libroIsbnS.getId().longValue());

    }
}
