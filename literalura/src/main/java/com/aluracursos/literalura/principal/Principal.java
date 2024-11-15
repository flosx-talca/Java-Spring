package com.aluracursos.literalura.principal;
import com.aluracursos.literalura.service.ConsumoApi;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/?search=dickens%20great";
    //private final String API_KEY = "&apikey=5e5aa900";
    //private ConvierteDatos conversor = new ConvierteDatos();


    public void muestraElMenu(){
        System.out.println(URL_BASE);
        var json = consumoApi.obtenerDatos(URL_BASE);
        System.out.printf("Prueba de JSON: %s", json);
        //var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        String  menu ="""
                1 - Hola mundos
                """;
        System.out.println(menu);
    }
}
