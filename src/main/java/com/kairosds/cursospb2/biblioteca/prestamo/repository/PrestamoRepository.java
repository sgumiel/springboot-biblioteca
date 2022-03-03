package com.kairosds.cursospb2.biblioteca.prestamo.repository;

import com.kairosds.cursospb2.biblioteca.prestamo.domain.Prestamo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends CrudRepository<Prestamo, Long> {

    List<Prestamo> findBybiblioteca1(String biblioteca1Code);
}
