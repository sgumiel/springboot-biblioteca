package com.kairosds.cursospb2.biblioteca.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "biblioteca.data")
public class BibliotecaDataConfiguration implements InitializingBean {

    private String codigo;

    @Override
    public void afterPropertiesSet() {
        if (this.codigo == null) {
            throw new IllegalArgumentException("Biblioteca codigo must be configured");
        }
    }
}