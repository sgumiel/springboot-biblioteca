package com.kairosds.cursospb2.biblioteca.requestlibro.service;

import com.kairosds.cursospb2.biblioteca.config.BibliotecaServicesConfiguration;
import com.kairosds.cursospb2.biblioteca.libro.repository.LibroRepository;
import com.kairosds.cursospb2.biblioteca.requestlibro.client.ReceiveRequestLibroClient;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.RequestLibro;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.exception.RequestLibroBibliotecaNotExist;
import com.kairosds.cursospb2.biblioteca.requestlibro.domain.exception.RequestLibroLibroNotExist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class RequestLibroServiceImpl implements RequestLibroService {

    private ReceiveRequestLibroClient receiveRequestLibroClient;
    private LibroRepository libroRepository;
    private BibliotecaServicesConfiguration bibliotecaServicesConfiguration;

    @Override
    public Boolean requestLibro(RequestLibro requestLibro) {

        final var libroCode = requestLibro.getLibroCode();
        final var libroExists = this.libroRepository.existsByCodigo(libroCode);

        if (!libroExists) {
            throw new RequestLibroLibroNotExist(libroCode);
        }

        final var bibliotecaCode = requestLibro.getBiliotecaCode();
        final var bibliotecaExists = this.bibliotecaServicesConfiguration.isBibliotecaConfigured(bibliotecaCode);

        if(!bibliotecaExists) {
            throw new RequestLibroBibliotecaNotExist(bibliotecaCode);
        }

        final var hayPrestamo = this.receiveRequestLibroClient.requestLibro(requestLibro);
        return hayPrestamo;

    }
}
