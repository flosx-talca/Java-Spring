package com.aluracursos.screenmatch.principal;


import com.aluracursos.screenmatch.service.ConsumoApi;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi  consumoApi = new ConsumoApi();
    private final String URL_BASE= "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=5e5aa900";


    public void muestraElMenu(){
        System.out.println("Ingrese el nombre de la Serie para buscar");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+ nombreSerie.replace(" ", "+") + API_KEY);
        System.out.println(json);
    }

}
