package com.kairosds.cursospb2.biblioteca.requestlibro.function;

import com.kairosds.cursospb2.biblioteca.requestlibro.domain.exception.RecieveRequestLibroException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class ExtractRequestLibroResponse implements Function<ClientResponse, Mono<Boolean>> {

    @Override
    public Mono<Boolean> apply(ClientResponse clientResponse) {

        if (clientResponse.statusCode() == HttpStatus.OK) {
            return clientResponse.bodyToMono(Boolean.class);
        } else {
            throw new RecieveRequestLibroException(clientResponse.statusCode().value());
        }
    }
}
