package com.aluracursos.literalura.model;

import java.util.Collections;
import java.util.List;

public class Libro {

    private String titulo;
    private List<Autor> autor;
    private Long numeroDescargas;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.numeroDescargas = datosLibro.numeroDescargas();

        //this.idiomas = datosLibro.idiomas();
        //this.numeroDescargas = datosLibro.numeroDescargas();
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }



    public Long getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Long numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
