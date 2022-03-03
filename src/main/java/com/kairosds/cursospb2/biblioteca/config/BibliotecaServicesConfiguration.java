package com.kairosds.cursospb2.biblioteca.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "bibliotecas-services")
public class BibliotecaServicesConfiguration {

    private String receiveRequestLibroUrl;
    private Map<String, BiblioRestConfig> mapRest;

    public String getBibliotecaUrl(String bibliotecaCode) {
        final var port = this.mapRest.get(bibliotecaCode).getPort();
        final var url = String.format(this.receiveRequestLibroUrl, port);
        return url;
    }

    public Boolean isBibliotecaConfigured(String bibliotecaCode) {
        return mapRest.get(bibliotecaCode)!=null;

    }

}
