package com.aluracursos.desafiospring.principal;

import com.aluracursos.desafiospring.models.Datos;
import com.aluracursos.desafiospring.models.DatosLibros;
import com.aluracursos.desafiospring.service.ConsumoAPI;
import com.aluracursos.desafiospring.service.ConvierteDatos;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
     private ConsumoAPI consumoAPI = new ConsumoAPI();
     private ConvierteDatos conversor = new ConvierteDatos();
     private Scanner teclado = new Scanner(System.in);

    public void muestraElMenu(){

        var json = consumoAPI.obtenerDatos(URL_BASE);
        //System.out.println(URL_BASE);
        //System.out.println(json);
        var datos = conversor.obtenerDatos(json, Datos.class);

        System.out.println(datos);

        //TOP 10 libros mas descargados

        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                .map(l->l.titulo().toUpperCase())
                .forEach(System.out::println);
        //BUSQUEDA de libros por nombre
        System.out.println("Ingrese nombre a buscar");
        var tituloLibro = teclado.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l-> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println("Libro encontrado");
            System.out.println(libroBuscado);

        }
        else{
            System.out.println("Libro  no encontrado");
        }
        // Trabajando con estadisticas
        DoubleSummaryStatistics est = datos.resultados().stream()
                .filter(d->d.numeroDeDescargas()>0)
                .collect(Collectors.summarizingDouble(DatosLibros::numeroDeDescargas));
        System.out.println("Cantidad media de descargas: " + est.getAverage());
        System.out.println("Cantidad max de descargas: " + est.getMax());
        System.out.println("Cantidad media de descargas: " + est.getMin());
        System.out.println("Cantidad registros evaluados para las estadisticas: " + est.getCount());

    }
}
