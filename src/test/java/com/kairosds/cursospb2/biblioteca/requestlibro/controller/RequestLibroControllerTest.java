package com.kairosds.cursospb2.biblioteca.requestlibro.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kairosds.cursospb2.biblioteca.apierror.ApiError;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.RequestLibro;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.error.RequestLibroErrors;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.exception.RequestLibroBibliotecaNotExist;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.exception.RequestLibroLibroNotExist;
import com.kairosds.cursospb2.biblioteca.requestlibro.service.RequestLibroService;
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
@WebMvcTest(controllers = RequestLibroController.class)
public class RequestLibroControllerTest {

    private static final String REQUEST_LIBRO_PATH = "/request-libro";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RequestLibroService requestLibroService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Request libro when libro code does not exist")
    void requestLibroWhenLibroCodeNotExist() throws Exception {

        final var libroCode = "K74D5FG7VB";
        final var requestLibro = RequestLibro.builder()
                .libroCode(libroCode)
                .biliotecaCode("BLIBLIMAD001").build();

        Mockito.when(this.requestLibroService.requestLibro(ArgumentMatchers.any(RequestLibro.class)))
                .thenThrow(new RequestLibroLibroNotExist(libroCode));

        final var requestLibroJsonString = this.objectMapper.writeValueAsString(requestLibro);

        final var resultString = mockMvc
                .perform(MockMvcRequestBuilders.post(REQUEST_LIBRO_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestLibroJsonString))
                .andReturn().getResponse().getContentAsString();

        final var requestLibroError = this.objectMapper.readValue(resultString, new TypeReference<ApiError>() {});

        Assertions.assertThat(requestLibroError.getCode()).isEqualTo(RequestLibroErrors.LIBRO_CODE_NOT_EXISTS.getCode());

    }

    @Test
    @DisplayName("Request libro when biblioteca code does not exist")
    void requestLibroWhenBibliotecaCodeNotExist() throws Exception {

        final var bibliotecaCode = "BLIBLIMAD001";
        final var requestLibro = RequestLibro.builder()
                .libroCode("K74D5FG7VB")
                .biliotecaCode(bibliotecaCode).build();

        Mockito.when(this.requestLibroService.requestLibro(ArgumentMatchers.any(RequestLibro.class)))
                .thenThrow(new RequestLibroBibliotecaNotExist(bibliotecaCode));

        final var requestLibroJsonString = this.objectMapper.writeValueAsString(requestLibro);

        final var resultString = mockMvc
                .perform(MockMvcRequestBuilders.post(REQUEST_LIBRO_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestLibroJsonString))
                .andReturn().getResponse().getContentAsString();

        final var requestLibroError = this.objectMapper.readValue(resultString, new TypeReference<ApiError>() {});

        Assertions.assertThat(requestLibroError.getCode()).isEqualTo(RequestLibroErrors.BIBLIOTECA_CODE_NOT_EXIST.getCode());

    }

    @Test
    @DisplayName("Request libro when the response of the request is OK (true)")
    void requestLibroWhenTheResposneofTheRequestIsTrue() throws Exception {

        final var bibliotecaCode = "BLIBLIMAD001";
        final var requestLibro = RequestLibro.builder()
                .libroCode("K74D5FG7VB")
                .biliotecaCode(bibliotecaCode).build();

        Mockito.when(this.requestLibroService.requestLibro(ArgumentMatchers.any(RequestLibro.class)))
                .thenReturn(true);

        final var requestLibroJsonString = this.objectMapper.writeValueAsString(requestLibro);

        final var resultString = mockMvc
                .perform(MockMvcRequestBuilders.post(REQUEST_LIBRO_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestLibroJsonString))
                .andReturn().getResponse().getContentAsString();

        final var requestLibroOK = this.objectMapper.readValue(resultString, new TypeReference<Boolean>() {});

        org.junit.jupiter.api.Assertions.assertTrue(requestLibroOK);

    }

    @Test
    @DisplayName("Request libro when the response of the request is OK (false)")
    void requestLibroWhenTheResposneofTheRequestIsFalse() throws Exception {

        final var bibliotecaCode = "BLIBLIMAD001";
        final var requestLibro = RequestLibro.builder()
                .libroCode("K74D5FG7VB")
                .biliotecaCode(bibliotecaCode).build();

        Mockito.when(this.requestLibroService.requestLibro(ArgumentMatchers.any(RequestLibro.class)))
                .thenReturn(false);

        final var requestLibroJsonString = this.objectMapper.writeValueAsString(requestLibro);

        final var resultString = mockMvc
                .perform(MockMvcRequestBuilders.post(REQUEST_LIBRO_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestLibroJsonString))
                .andReturn().getResponse().getContentAsString();

        final var requestLibroFalse = this.objectMapper.readValue(resultString, new TypeReference<Boolean>() {});

        org.junit.jupiter.api.Assertions.assertFalse(requestLibroFalse);

    }
}
