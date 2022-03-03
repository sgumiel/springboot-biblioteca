package com.kairosds.cursospb2.biblioteca.receiverequestlibro.controller;

import com.kairosds.cursospb2.biblioteca.receiverequestlibro.service.ReceiveRequestLibroService;
import com.kairosds.cursospb2.biblioteca.receiverequestlibro.domain.ReceiveRequestLibro;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/receive-request-libro")
public class ReceiveRequestLibroController {

    final ReceiveRequestLibroService requestLibroService;

    @PostMapping
    public ResponseEntity<Boolean> receiveRequestLibro(@RequestBody ReceiveRequestLibro receiveRequestLibro) {

        final var response = this.requestLibroService.receiveRequestLibro(receiveRequestLibro);
        return ResponseEntity.ok(response);
    }
}
