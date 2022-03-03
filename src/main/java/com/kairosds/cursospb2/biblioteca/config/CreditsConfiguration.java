package com.kairosds.cursospb2.biblioteca.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "credits")
public class CreditsConfiguration {

    private Integer maxPerLibro;
    private Integer minCreditsToRequestLibro;
}
