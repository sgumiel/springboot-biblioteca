package com.kairosds.cursospb2.biblioteca.biblioteca.repository;

import com.kairosds.cursospb2.biblioteca.biblioteca.domain.BibliotecaLibro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecaLibroRepository extends CrudRepository<BibliotecaLibro, Long> {

    Boolean existsByLibroCodigo(String codigo);

    @Query("select count(bl) from BibliotecaLibro bl where bl.bibliotecaCodigo = :bCode and libroCodigo = :lCode")
    Integer countCopies(@Param("bCode") String bibliotecaCode, @Param("lCode") String libroCode);
}
