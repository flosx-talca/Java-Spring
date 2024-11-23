package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;

//@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    //@Query ("SELECT l FROM Libro l WHERE l.titulo = :titulo ORDER BY l.titulo asc LIMIT 1")
    //Libro findByTitulo1(String titulo);
    Libro findByTitulo(String titulo);

}
