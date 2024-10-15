package com.aluracursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // Para ignoarar las demas claves que no estan definisdas en Datos Serie
public record DatosSerie(

        @JsonAlias("Title") String titulo,
        @JsonAlias("totalSeasons")Integer totalDeTemporadas,
        @JsonAlias ("imbdRating") String evaluacion

) {
    //Cuerpo del record

}
