package com.aluracursos.literalura.principal;
import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.service.ConsumoApi;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.awt.image.SinglePixelPackedSampleModel;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Locale.filter;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private DatosLibro datosLibro;
    private final String URL_BASE = "https://gutendex.com/books/?search=dickens%20great";
    //private final String API_KEY = "&apikey=5e5aa900";
    private ConvierteDatos conversor = new ConvierteDatos();
    private Resultado dataResultado;
   // private DatosLibro datosLibro;
    //private ConvierteDatos conversor = new ConvierteDatos();



    public void muestraElMenu(){
        System.out.println(URL_BASE);
        var json = consumoApi.obtenerDatos(URL_BASE);

        System.out.println("Prueba de JSON: "+json);

        dataResultado = conversor.obtenerDatos(json, Resultado.class);
       // System.out.println("data resultyado");
        //System.out.println( dataResultado.libroresultado().getFirst());

        var busqueda = "dickens%20great";
       Optional<DatosLibro> datoLibro = dataResultado.libroresultado().stream()
                .filter(d->d.titulo().toUpperCase().contains(busqueda.toUpperCase()))
                .findFirst();

        //System.out.println(dataResultado.libroresultado().stream().map(e-> new DatosLibro(e.titulo(),e.numeroDescargas())));
        List <DatosLibro> libro  =  dataResultado.libroresultado().stream()
        .map(e-> new DatosLibro(e.titulo(),e.autor(), e.numeroDescargas()))
                .collect(Collectors.toList());
        System.out.println("HOLA");
        System.out.println(libro.getLast());
        //

        List<DatosAutor> autor = libro.stream().flatMap(a->a.autor().stream()


        ).collect(Collectors.toList());

        System.out.println("Probadno stream con autor"+autor.getFirst());

        Autor autor2 = new Autor(autor.getFirst());

        System.out.println("Veamos la clase"+autor2.getNombre()+" "+autor2.getFechaNacimiento());



        //System.out.println(autor);



       /* List <Episodio> episodios = temporadas.stream()
                .flatMap(d->d.episodios().stream()
                        .map(e-> new Episodio(d.numero(),e)))
                .collect(Collectors.toList());*/


        //System.out.println(datoLibro);
        /*
        //var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        String  menu ="""
                1 - Hola mundos
                """;
        System.out.println(menu);*/

    }
}
