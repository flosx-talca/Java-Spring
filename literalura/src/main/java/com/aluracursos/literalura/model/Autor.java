package com.aluracursos.literalura.model;
import com.aluracursos.literalura.repository.AutorRepository;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaDefuncion;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Libro> libro;
    //private Libro libro;



    public Autor(){}
    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        this.fechaDefuncion = datosAutor.fechaDefuncion();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(Integer fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    public List<Libro> getLibro() {
        return libro;
    }

    @Override
    public String toString() {
        return "Nombre: "+ this.nombre;
    }

  /*  public void setLibro(List<Libro> libro) {
        this.libro = libro;
    }

    public String toString() {
        return (" Nombre: %s Fecha Nac: %d Fecha Fall: %d".formatted(nombre, fechaNacimiento, fechaNacimiento));
    }*/

}


