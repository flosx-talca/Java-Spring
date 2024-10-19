package com.aluracursos.screenmatch.model;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private  Integer numeroEpisodio;
    private  Double evaluacion;
    private LocalDate fechaDeLanzamiento;

    public Episodio(Integer numero, DatosEpisodio d) {
        this.temporada = numero;
        this.titulo = d.titulo();
        this.numeroEpisodio = d.numeroEpisodio();
        try {
            this.evaluacion = Double.valueOf (d.evaluacion());
        }catch (NumberFormatException e){
            this.evaluacion = 0.0;

        }
        try {
            this.fechaDeLanzamiento = LocalDate.parse(d.fechaDeLanzamiento());
        }catch (DateTimeException e){
            this.fechaDeLanzamiento = null;
        }



    }


    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTirulo() {
        return titulo;
    }

    public void setTirulo(String tirulo) {
        this.titulo = tirulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    @Override
    public String toString() {
        return
                "temorada= "+ temporada +
                 "Titulo = " + titulo +
                  "Numero Episodio = " + numeroEpisodio +
                   "Evaluacion = "+ evaluacion +
                   "fecha de Lanzamiento = "+ fechaDeLanzamiento;
    }
}