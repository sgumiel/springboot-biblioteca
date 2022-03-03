package com.kairosds.cursospb2.biblioteca.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "request-libros")
public class RequestLibrosConfig {

    private Integer defaultCopies;


}
