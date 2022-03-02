package com.kairosds.cursospb2.biblioteca.biblioteca.repository;

import com.kairosds.cursospb2.biblioteca.biblioteca.domain.BibliotecaLibro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecaLibroRepository extends CrudRepository<BibliotecaLibro, Long> {

    Boolean existsByLibroCodigo(String codigo);
}
