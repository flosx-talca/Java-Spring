package com.aluracursos.screenmatch.principal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EjemploStreams {

    public void muestraEjemplo(){
        List<String> nombres = Arrays.asList("Orlando", "Ricardo", "Maria", "Erick", "Nadia");
        nombres.stream()
                .sorted() //ordena
                .filter(n-> n.startsWith("R")) //filtra
                .map(n->n.toUpperCase()) // mayus
                .limit(4) //limita el tamaño a mostrar
                .forEach(System.out::println);

    }
}
