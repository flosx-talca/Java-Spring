package com.aluracursos.literalura.model;

import java.util.Collections;
import java.util.List;

public class Libro {

    private String titulo;
    private List<Autor> autor;
    private Long numeroDescargas;
    private String idioma;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.numeroDescargas = datosLibro.numeroDescargas();
        this.idioma = datosLibro.idioma().getFirst();
        this.numeroDescargas = datosLibro.numeroDescargas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    public Long getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Long numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return """
                ------------LIBRO--------------
                Titulo: %s
                Autor: %s
                Descargas: %s
                Lenguajes: %s
                --------------------------------
                """.formatted(titulo, autor, numeroDescargas, idioma);
    }



}
