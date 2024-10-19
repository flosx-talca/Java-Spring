package com.aluracursos.desafiospring.service;

public interface IConvierteDatos {

         <T> T obtenerDatos (String json, Class <T> clase);
}
