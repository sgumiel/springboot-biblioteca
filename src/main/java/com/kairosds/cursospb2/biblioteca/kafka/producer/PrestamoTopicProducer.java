package com.kairosds.cursospb2.biblioteca.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PrestamoTopicProducer {

    private final KafkaTemplate kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void publicar(PrestamoTopicDTO prestamoTopicDTO) throws JsonProcessingException {

        final var data = this.objectMapper.writeValueAsString(prestamoTopicDTO);

        this.kafkaTemplate.send("topic-pelicula", data);

        System.out.println(prestamoTopicDTO);
    }
}
