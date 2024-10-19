package com.aluracursos.desafiospring.principal;

import com.aluracursos.desafiospring.models.Datos;
import com.aluracursos.desafiospring.service.ConsumoAPI;
import com.aluracursos.desafiospring.service.ConvierteDatos;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
     ConsumoAPI consumoAPI = new ConsumoAPI();
     ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu(){

        var json = consumoAPI.obtenerDatos(URL_BASE);
        //System.out.println(URL_BASE);
        //System.out.println(json);
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);

    }
}
