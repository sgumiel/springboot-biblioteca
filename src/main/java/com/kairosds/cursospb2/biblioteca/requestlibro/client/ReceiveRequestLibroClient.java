package com.kairosds.cursospb2.biblioteca.requestlibro.client;

import com.kairosds.cursospb2.biblioteca.requestlibro.domain.RequestLibro;

public interface ReceiveRequestLibroClient {

    Boolean requestLibro(RequestLibro requestLibro);
}
