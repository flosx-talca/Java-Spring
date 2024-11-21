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
    private final String URL_BASE = "https://gutendex.com/books/?search=dickens%20great";
    private ConvierteDatos conversor = new ConvierteDatos();
    private Resultado dataResultado;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
            this.libroRepository =libroRepository;

            this.autorRepository = autorRepository;
    }


    public void inicioApp(){
        Integer opcionUsuario=0;
        do{
            mostrarMenu();
            opcionUsuario = solicitaOpccionTeclado();
            switch (opcionUsuario){
                case 1:
                    BuscarLibroPorTitulo();
                    break;
                case 2:
                    System.out.println("1");
                    break;
                case 3:
                    System.out.println("1");
                    break;
                case 4:
                    System.out.println("1");
                    break;
                case 5:
                    System.out.println("1");
                    break;
                default:
                    break;
            }

        }while (opcionUsuario != 0);





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

   }

   public void BuscarLibroPorTitulo(){


   }

   public void mostrarMenu(){
        String menu = """
                (1) - Buscar libro por titulo
                (2) - Listar Libros registrados
                (3) - Listar Autores registrados
                (4) - Listar Autores vivos determinados año
                (5) - Listar Libro por idioma
                (0) - Salir""";
       System.out.println(menu);
   }

    public Integer solicitaOpccionTeclado() {
        Integer op = 999;

        try {
            System.out.print("\nIngresa la opción: ");
            op = teclado.nextInt();


        } catch (Exception e) {
             System.out.println("Debe ingresar un dato valido 1 al 5: " + e);

        }
        teclado.nextLine();
        return op;

    }

}
