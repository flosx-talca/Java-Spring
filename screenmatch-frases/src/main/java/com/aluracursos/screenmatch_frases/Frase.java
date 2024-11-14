package com.aluracursos.screenmatch_frases;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Modifying;

import java.sql.Struct;
@Entity
@Table(name = "frases")
public class Frase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String frase;
    private String personaje;
    private String poster;

}
