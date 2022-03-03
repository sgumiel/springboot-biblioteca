package com.kairosds.cursospb2.biblioteca.requestlibro.client;

import com.kairosds.cursospb2.biblioteca.config.BibliotecaDataConfiguration;
import com.kairosds.cursospb2.biblioteca.config.BibliotecaServicesConfiguration;
import com.kairosds.cursospb2.biblioteca.receiverequestlibro.domain.ReceiveRequestLibro;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.RequestLibro;
import com.kairosds.cursospb2.biblioteca.requestlibro.function.ExtractRequestLibroResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@AllArgsConstructor
public class ReceiveRequestLibroClientImpl implements ReceiveRequestLibroClient {

    private final WebClient webClient;
    private final BibliotecaServicesConfiguration bibliotecaServicesConfiguration;
    private final BibliotecaDataConfiguration bibliotecaDataConfiguration;
    private final ExtractRequestLibroResponse extractRequestLibroResponse;

    @Override
    public Boolean requestLibro(RequestLibro requestLibro) {


        final var bibliotecaCodigo = requestLibro.getBiliotecaCode();

        final var receiveRequestLibro = ReceiveRequestLibro.builder()
                .libroCode(requestLibro.getLibroCode())
                .biliotecaCode(this.bibliotecaDataConfiguration.getCodigo())
                .unidades(requestLibro.getUnidades())
                        .build();

        final var url = this.bibliotecaServicesConfiguration.getBibliotecaUrl(bibliotecaCodigo);
        final var response = webClient.post()
                .uri(url)
                .body(BodyInserters.fromValue(receiveRequestLibro))
                .exchangeToMono(this.extractRequestLibroResponse).block();

        return response;
    }
}
