package com.aluracursos.literalura.principal;
import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoApi;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.*;

public class Principal {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();
    private DatosLibro datosLibro;
    private Libro libro;
    private DatosAutor datosAutor;
    private Autor autor;
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private final ConvierteDatos conversor = new ConvierteDatos();
    private Resultado dataResultado;


    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {

            this.libroRepository =libroRepository;
            this.autorRepository = autorRepository;
    }

    public void inicioApp(){

        int opcionUsuario;
        do{
            mostrarMenu();
            opcionUsuario = solicitaOpccionTeclado();
            switch (opcionUsuario){

                case 1:
                    BuscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    System.out.println("5");
                    break;
                default:
                    break;
            }

        }while (opcionUsuario != 0);
   }

   public void BuscarLibroPorTitulo(){
       String libroPorTeclado = solicitaLibroTeclado();
       //System.out.println(libroPorTeclado);
       var json = consumoApi.obtenerDatos(URL_BASE+libroPorTeclado.replace(" ", "%20"));
       //System.out.println(URL_BASE);
       //System.out.println("Prueba de JSON: " + json);
       dataResultado = conversor.obtenerDatos(json, Resultado.class);
       //String libroPorTeclado = "Great Expectation";
       Optional<DatosLibro> dLibro = dataResultado.libroresultado().stream()
               .filter(d -> d.titulo().toUpperCase().contains(libroPorTeclado.toUpperCase()))
               //.filter(d -> d.titulo().equalsIgnoreCase(libroPorTeclado))
               .findFirst();

       if(dLibro.isPresent()) {
           datosLibro = dLibro.get();
           Optional <Libro> libroBuscadoBD = Optional.ofNullable(libroRepository.findByTitulo(datosLibro.titulo()));

           if(libroBuscadoBD.isEmpty()){ //VACIO BD
               //libro = dlibro.get();
               libro = new Libro(datosLibro);
               //System.out.println(libro.getTitulo()+ libro.getAutor() +" MOSTRADOS");
               Optional<DatosAutor> dAutor = datosLibro.autor().stream().findFirst();

               if (dAutor.isPresent()) {
                   datosAutor = dAutor.get();
                   Optional<Autor> autorBuscadoBD= Optional.ofNullable(autorRepository.findByNombre(datosAutor.nombre()));

                   if(autorBuscadoBD.isEmpty()){ //VACIO BD
                       autor = new Autor(datosAutor);
                       autorRepository.save(autor);
                       libro.setAutor(autor);
                       libroRepository.save(libro);
                   }
                   else{
                       System.out.println("El Autor existe en la Base de Datos");
                       autor = autorBuscadoBD.get();
                       libro.setAutor(autor);
                       libroRepository.save(libro);
                   }
                 //  System.out.println("Autor existe " + dAutor);
                   // List<Autor> listaAutor = Arrays.asList(autor);

               } else {
                   System.out.println("Autor no existe en la API");
               }
           }
           else{
               System.out.println("Existe Libro en la Base de datos Local");
           }

       } else {
           System.out.println("Libro NO encontrado en la API");
       }
   }

   public void mostrarMenu(){
        String menu = """
                ------------------------------
                    MANTENEDOR API LIBROS
                (1) - Buscar libro por titulo
                (2) - Listar Libros registrados
                (3) - Listar Autores registrados
                (4) - Listar Autores vivos determinados año
                (5) - Listar Libro por idioma
                (0) - Salir
                -------------------------------""";
       System.out.println(menu);
   }

    public Integer solicitaOpccionTeclado() {
        int op=999;

        try {
            System.out.print("\nIngresa la opción: ");
            op = teclado.nextInt();

        } catch (Exception e) {
             System.out.println("Debe ingresar un dato valido 1 al 5: " + e);

        }
        teclado.nextLine();
        return op;
    }

    public String solicitaLibroTeclado() {
        String solicitaLibro="";

        while (solicitaLibro.isEmpty()) {
            try {
                System.out.print("\nIngrese nombre del libro: ");
                solicitaLibro = teclado.nextLine();

                if (solicitaLibro.isEmpty()) {
                    throw new IllegalArgumentException("La entrada no puede estar vacía, ingrese nuevamente");
                }
            } catch (Exception e) {
                System.out.println("Debe ingresar un dato valido 1 al 5: " + e);
            }
        }
        return solicitaLibro;
    }

    public void listarLibrosRegistrados(){

        List<Libro> librosRegistrados = libroRepository.findAll();
        librosRegistrados.forEach(System.out::println);

    }

    public void listarAutoresRegistrados(){

        List<Autor> autoresRegistrados = autorRepository.findAll();
        autoresRegistrados.forEach(System.out::println);

    }

    public void listarAutoresVivos(){
        List<Autor> autoresVivos  = autorRepository.buscaAutorVivoanio(2024);
        autoresVivos.forEach(System.out::println);
    }




}
