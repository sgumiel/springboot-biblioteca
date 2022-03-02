package com.kairosds.cursospb2.biblioteca.libro.repository;

import com.kairosds.cursospb2.biblioteca.libro.domain.Libro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends CrudRepository<Libro, Long> {

    Boolean existsByCodigo(String codigo);
}
