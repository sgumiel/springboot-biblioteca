package com.kairosds.cursospb2.biblioteca.libroisbn.repository;

import com.kairosds.cursospb2.biblioteca.libroisbn.domain.LibroISBN;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroIsbnRepository extends CrudRepository<LibroISBN, Long> {

    Boolean existsByIsbn(String isbn);
}
