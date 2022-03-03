package com.kairosds.cursospb2.biblioteca.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Data
@Configuration
public class BibliotecaConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
