package com.kairosds.cursospb2.biblioteca.scheduling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kairosds.cursospb2.biblioteca.config.BibliotecaDataConfiguration;
import com.kairosds.cursospb2.biblioteca.kafka.producer.PrestamoTopicDTO;
import com.kairosds.cursospb2.biblioteca.kafka.producer.PrestamoTopicProducer;
import com.kairosds.cursospb2.biblioteca.libro.repository.LibroRepository;
import com.kairosds.cursospb2.biblioteca.prestamo.repository.PrestamoRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@AllArgsConstructor
public class DevolverLibro {

    private final PrestamoRepository prestamoRepository;
    private final BibliotecaDataConfiguration bibliotecaDataConfiguration;
    private final PrestamoTopicProducer prestamoTopicProducer;
    private final LibroRepository libroRepository;

    @Scheduled(fixedRate = 30000)
    public void devolverLibro(){

        final var bibliotecaYo = this.bibliotecaDataConfiguration.getCodigo();

        final var prestamoList = this.prestamoRepository.findBybiblioteca1(bibliotecaYo);

        final var ahora = OffsetDateTime.now();
        prestamoList.stream()
                .filter(prestamo -> !ahora.isAfter(prestamo.getReturndate()))
                .forEach(prestamo -> {
                    final  var biblioteca = prestamo.getBiblioteca2();

                    final var libro = this.libroRepository.findByCodigo(prestamo.getLibrocode()).get();
                    libro.setStatus(false);
                    this.libroRepository.save(libro);
                    final var creditos = 6;

                    final var prestamotopicDto = PrestamoTopicDTO.builder()
                            .bibliotca(biblioteca).creditos(creditos).build();

                    try {
                        this.prestamoTopicProducer.publicar(prestamotopicDto);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                });
    }
}
