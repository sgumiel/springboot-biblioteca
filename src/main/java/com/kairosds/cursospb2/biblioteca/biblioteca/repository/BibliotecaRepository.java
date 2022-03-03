package com.kairosds.cursospb2.biblioteca.biblioteca.repository;

import com.kairosds.cursospb2.biblioteca.biblioteca.domain.Biblioteca;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecaRepository extends CrudRepository<Biblioteca, Long> {

    @Query("select b.creditos from Biblioteca b where b.code = :code")
    Integer findCreditosByCode(@Param("code") String code);
}
