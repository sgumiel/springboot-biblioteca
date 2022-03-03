package com.kairosds.cursospb2.biblioteca.requestlibro.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kairosds.cursospb2.biblioteca.config.BibliotecaDataConfiguration;
import com.kairosds.cursospb2.biblioteca.config.BibliotecaServicesConfiguration;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.RequestLibro;
import com.kairosds.cursospb2.biblioteca.requestlibro.function.ExtractRequestLibroResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ActiveProfiles("test")
@SpringBootTest(classes = ReceiveRequestLibroClientImpl.class)
@MockServerTest("server.url=http://localhost:${mock-server.port}")
@Import(WebClientConfig.class)
public class ReceiveRequestLibroClientTest {

    private MockServerClient mockServerClient;

    private static final String MOCK_SERVER = "http://localhost:%s";
    private static final String RECEIVE_REQUEST_LIBRO_URI = "/biblioteca/receive-request-libro";

    @Autowired
    private ReceiveRequestLibroClient receiveRequestLibroClient;

    @MockBean
    private BibliotecaServicesConfiguration bibliotecaServicesConfiguration;

    @MockBean
    private BibliotecaDataConfiguration bibliotecaDataConfiguration;

    @MockBean
    private ExtractRequestLibroResponse extractRequestLibroResponse;

    @Test
    @DisplayName("Request Libro success true")
    void requestLibroSuccessTrue() throws JsonProcessingException {

        final var libroCode = "A47HUR7J65";
        final var bibliotecaCode = "L9D5FBVC4R";

        final var requestLibro = RequestLibro.builder()
                .libroCode(libroCode)
                .biliotecaCode(bibliotecaCode)
                .unidades(2).build();

        final var responseBody = "true";
        mockServerClient.when(
                request().withMethod("POST").withPath(RECEIVE_REQUEST_LIBRO_URI))
                .respond(response()
                        .withStatusCode(200)
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(responseBody)
                );

        Mockito.when(this.bibliotecaDataConfiguration.getCodigo())
                .thenReturn("BIBLIMAD01");

        final var url = new StringBuilder()
                .append(String.format(MOCK_SERVER, mockServerClient.getPort()))
                .append(RECEIVE_REQUEST_LIBRO_URI)
                .toString();
        Mockito.when(this.bibliotecaServicesConfiguration.getBibliotecaUrl(ArgumentMatchers.anyString()))
                .thenReturn(url);

        Mockito.when(extractRequestLibroResponse.apply(ArgumentMatchers.any(ClientResponse.class)))
                .thenReturn(Mono.just(true));

        final var requestLibroResponse = this.receiveRequestLibroClient.requestLibro(requestLibro);

        Assertions.assertTrue(requestLibroResponse);
    }

    @Test
    @DisplayName("Request Libro success false")
    void requestLibroSuccessFalse() throws JsonProcessingException {

        final var libroCode = "A47HUR7J65";
        final var bibliotecaCode = "L9D5FBVC4R";

        final var requestLibro = RequestLibro.builder()
                .libroCode(libroCode)
                .biliotecaCode(bibliotecaCode)
                .unidades(2).build();

        final var responseBody = "true";
        mockServerClient.when(
                        request().withMethod("POST").withPath(RECEIVE_REQUEST_LIBRO_URI))
                .respond(response()
                        .withStatusCode(200)
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(responseBody)
                );

        Mockito.when(this.bibliotecaDataConfiguration.getCodigo())
                .thenReturn("BIBLIMAD01");

        final var url = new StringBuilder()
                .append(String.format(MOCK_SERVER, mockServerClient.getPort()))
                .append(RECEIVE_REQUEST_LIBRO_URI)
                .toString();
        Mockito.when(this.bibliotecaServicesConfiguration.getBibliotecaUrl(ArgumentMatchers.anyString()))
                .thenReturn(url);

        Mockito.when(extractRequestLibroResponse.apply(ArgumentMatchers.any(ClientResponse.class)))
                .thenReturn(Mono.just(false));

        final var requestLibroResponse = this.receiveRequestLibroClient.requestLibro(requestLibro);

        Assertions.assertFalse(requestLibroResponse);
    }
}
