package com.kairosds.cursospb2.biblioteca.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PrestamoDTOConsumer {

    @KafkaListener(topics = "${topics.pelicula}")
    public void consumir(String data) {
        System.out.println(data);
    }
}
