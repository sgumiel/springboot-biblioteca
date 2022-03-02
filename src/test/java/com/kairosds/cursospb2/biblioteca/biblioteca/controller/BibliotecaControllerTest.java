package com.kairosds.cursospb2.biblioteca.biblioteca.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kairosds.cursospb2.biblioteca.apierror.ApiError;
import com.kairosds.cursospb2.biblioteca.biblioteca.domain.error.AssociateLibroBibliotecaErrors;
import com.kairosds.cursospb2.biblioteca.biblioteca.domain.exception.AssociateLibroBiliotecaLibroAlreadyAssociated;
import com.kairosds.cursospb2.biblioteca.biblioteca.domain.exception.AssociateLibroBiliotecaLibroCodeNotExist;
import com.kairosds.cursospb2.biblioteca.biblioteca.service.BibliotecaService;
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

@WebMvcTest(controllers = BibliotecaController.class)
public class BibliotecaControllerTest {

    private static final String BIBLIOTECA_PATH = "/biblioteca";
    private static final String ASSOCIATE_LIBRO_PATH = BIBLIOTECA_PATH + "/%s";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BibliotecaService bibliotecaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Associate libro to biblioteca when codigo of libro does not exists")
    void associateWhenLibroCodigoDoesNotExist() throws Exception {

        final var codigo = "9KB5G67TA1";

        Mockito.when(bibliotecaService.associateLibro(ArgumentMatchers.anyString()))
                .thenThrow(new AssociateLibroBiliotecaLibroCodeNotExist(codigo));

        final var associateUri = String.format(ASSOCIATE_LIBRO_PATH, codigo);

        final var resultString = mockMvc
                .perform(MockMvcRequestBuilders.post(associateUri)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        final var associatedLibro = this.objectMapper.readValue(resultString, new TypeReference<ApiError>() {});

        Assertions.assertThat(associatedLibro.getCode()).isEqualTo(AssociateLibroBibliotecaErrors.LIBRO_CODE_NOT_EXISTS.getCode());
    }

    @Test
    @DisplayName("Associate libro to biblioteca when libro already is associated")
    void associateWhenLibroCodigoIsAlreadyAssociated() throws Exception {

        final var codigo = "9KB5G67TA1";

        Mockito.when(bibliotecaService.associateLibro(ArgumentMatchers.anyString()))
                .thenThrow(new AssociateLibroBiliotecaLibroAlreadyAssociated(codigo));

        final var associateUri = String.format(ASSOCIATE_LIBRO_PATH, codigo);

        final var resultString = mockMvc
                .perform(MockMvcRequestBuilders.post(associateUri)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        final var associatedLibro = this.objectMapper.readValue(resultString, new TypeReference<ApiError>() {});

        Assertions.assertThat(associatedLibro.getCode()).isEqualTo(AssociateLibroBibliotecaErrors.LIBRO_CODE_ALREADY_ASSOCIATE.getCode());
    }

    @Test
    @DisplayName("Associate libro to biblioteca success")
    void associateLibroBibliotecaSuccess() throws Exception {

        final var codigo = "9KB5G67TA1";

        Mockito.when(bibliotecaService.associateLibro(ArgumentMatchers.anyString()))
                .thenReturn(true);

        final var associateUri = String.format(ASSOCIATE_LIBRO_PATH, codigo);

        final var resultString = mockMvc
                .perform(MockMvcRequestBuilders.post(associateUri)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        final var associatedLibro = this.objectMapper.readValue(resultString, new TypeReference<Boolean>() {});

        org.junit.jupiter.api.Assertions.assertTrue(associatedLibro);
    }
}
