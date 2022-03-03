package com.kairosds.cursospb2.biblioteca.receiverequestlibro.service;

import com.kairosds.cursospb2.biblioteca.biblioteca.repository.BibliotecaLibroRepository;
import com.kairosds.cursospb2.biblioteca.biblioteca.repository.BibliotecaRepository;
import com.kairosds.cursospb2.biblioteca.config.BibliotecaDataConfiguration;
import com.kairosds.cursospb2.biblioteca.config.CreditsConfiguration;
import com.kairosds.cursospb2.biblioteca.config.RequestLibrosConfig;
import com.kairosds.cursospb2.biblioteca.libro.repository.LibroRepository;
import com.kairosds.cursospb2.biblioteca.prestamo.domain.Prestamo;
import com.kairosds.cursospb2.biblioteca.prestamo.repository.PrestamoRepository;
import com.kairosds.cursospb2.biblioteca.receiverequestlibro.domain.ReceiveRequestLibro;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@AllArgsConstructor
public class ReceiveRequestLibroServiceImpl implements ReceiveRequestLibroService {

    private final BibliotecaRepository bibliotecaRepository;
    private final CreditsConfiguration creditsConfiguration;
    private final RequestLibrosConfig requestLibrosConfig;
    private final BibliotecaDataConfiguration bibliotecaDataConfiguration;
    private final LibroRepository libroRepository;
    private final BibliotecaLibroRepository bibliotecaLibroRepository;
    private final PrestamoRepository prestamoRepository;

    @Override
    public Boolean receiveRequestLibro(ReceiveRequestLibro requestLibro) {

        final var bibliotecaCode = requestLibro.getBiliotecaCode();

        final var credits = this.bibliotecaRepository.findCreditosByCode(bibliotecaCode);
        final var minCredtisToRequestLibro = this.creditsConfiguration.getMinCreditsToRequestLibro();

        if (credits < minCredtisToRequestLibro) return false;

        final var libroCode = requestLibro.getLibroCode();
        final var existsLibro = this.libroRepository.existsByCodigo(libroCode);

        if (!existsLibro) return false;

        var numLibrosSolicitados = requestLibro.getUnidades();
        if (numLibrosSolicitados == null) {
            numLibrosSolicitados = requestLibrosConfig.getDefaultCopies();
        }

        final var bibliotecaYo = this.bibliotecaDataConfiguration.getCodigo();
        final var numCopiasMias = this.bibliotecaLibroRepository.countCopies(bibliotecaYo, libroCode);

        if(numLibrosSolicitados>numCopiasMias) return false;

        final var periodo = 3;
        final var prestamo = Prestamo.builder()
                .biblioteca1(bibliotecaCode)
                .biblioteca2(bibliotecaYo)
                .librocode(libroCode)
                .lenddate(OffsetDateTime.now())
                .returndate(OffsetDateTime.now().plusMinutes(periodo))
                .build();

        final var prestamoSaved = this.prestamoRepository.save(prestamo);

        final var libro = this.libroRepository.findByCodigo(libroCode).get();
        libro.setStatus(true);

        this.libroRepository.save(libro);

        return true;
    }
}
