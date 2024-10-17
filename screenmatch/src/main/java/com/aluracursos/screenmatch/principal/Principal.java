package com.aluracursos.screenmatch.principal;


import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.service.ConsumoApi;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi  consumoApi = new ConsumoApi();
    private final String URL_BASE= "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=5e5aa900";
    private ConvierteDatos conversor = new ConvierteDatos();


    public void muestraElMenu(){
        System.out.println("Ingrese el nombre de la Serie para buscar");
        //BUSCA los datos de la serie
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+ nombreSerie.replace(" ", "+") + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);
        //BUSCA los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <=datos.totalDeTemporadas() ; i++) {
            //System.out.println(URL_BASE + nombreSerie.replace(" ", "+")+ "/?Season=" +  i +API_KEY);
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+")+ "&Season=" +  i +API_KEY);
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);


        }
        //temporadas.forEach(System.out::println);
        // recorrer list con for
       /*
        for (int i = 0; i < datos.totalDeTemporadas() ; i++) {
            List<DatosEpisodio> episodiosTemporada =  temporadas.get(i).episodios();
            System.out.println("Temporada "+ (i+1) + datos.titulo()+"..." );
            for (int j = 0; j < episodiosTemporada.size() ; j++) {
                System.out.println(j + ".- "+ episodiosTemporada.get(j).titulo());

            }

        }*/
        // FUncion lambda para imprimir todos los episodios

        //temporadas.forEach(t ->  t.episodios().forEach(e-> System.out.println(e.titulo())));

       //COnvertir todas las informaciones a una lista del tipo DAtosEpisodios
        System.out.println("Episodios");
        List<DatosEpisodio> datosEpisodios  = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());
        //TOP 5 episodios

        datosEpisodios.stream()
                .filter(e-> !e.evaluacion().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .limit(5)
                .forEach(System.out::println);

        
    }

}
