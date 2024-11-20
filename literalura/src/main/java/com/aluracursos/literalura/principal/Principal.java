package com.aluracursos.literalura.principal;
import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoApi;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.lang.reflect.Array;
import java.util.*;

import static java.util.Locale.filter;

public class Principal {
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private DatosLibro datosLibro;
    private Libro libro;
    private DatosAutor datosAutor;
    private Autor autor;
    private final String URL_BASE = "https://gutendex.com/books/?search=dickens%20great";
    private ConvierteDatos conversor = new ConvierteDatos();
    private Resultado dataResultado;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
            this.libroRepository =libroRepository;

            this.autorRepository = autorRepository;
    }

    // para la interfaz SerieRepositoryn (Inyeccion de dependencia) lo crea intelli





    public void muestraElMenu(){
        System.out.println(URL_BASE);
        var json = consumoApi.obtenerDatos(URL_BASE);
        System.out.println("Prueba de JSON: "+json);

        dataResultado = conversor.obtenerDatos(json, Resultado.class);


        String busqueda = "Great Expectation";
        Optional<DatosLibro> dLibro = dataResultado.libroresultado().stream()
               .filter(d-> d.titulo().toUpperCase().contains(busqueda.toUpperCase()))
               .findFirst();
       if(dLibro.isPresent()){
           System.out.println("Libro encontrado");
           System.out.println(dLibro.get().autor().getFirst());
           datosLibro = dLibro.get();
           libro = new Libro(datosLibro);

       }
       else{
           System.out.println("No encontrado");
       }



        Optional<DatosAutor> dAutor = datosLibro.autor().stream().findFirst();

       if (dAutor.isPresent()){
           System.out.println("Autor existe "+ dAutor);
           datosAutor = dAutor.get();
           autor = new Autor(datosAutor);
           autorRepository.save(autor);
           libro.setAutor(autor);
           libroRepository.save(libro);
           List<Libro> libro2 = Arrays.asList(libro);
           //autor.setLibro(libro2);
           // List<Autor> listaAutor = Arrays.asList(autor);
            //libro.setAutor(listaAutor);
            //System.out.println(libro.getAutor().getFirst().getNombre());

       }
       else{
           System.out.println("Autor no existe");
       }

        System.out.println(libro);

        //System.out.println(libro.getAutor().getFirst());










/*
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

        // Optional<List<Autor>> datosAutorOptional = libro.stream().flatMap(a->a.autor().stream())
         //       .filter(e-> e.nombre().contains(busqueda.toUpperCase())).collect(Collectors.toList()));

        Autor autor2 = new Autor(autor.getFirst());

        System.out.println("Veamos la clase"+autor2.getNombre()+" "+autor2.getFechaNacimiento());



        //System.out.println(autor);

        */

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
