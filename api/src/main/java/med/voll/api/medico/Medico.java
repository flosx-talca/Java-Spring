package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.DatosDireccion;
import med.voll.api.direccion.Direccion;

@Table(name="medicos")
@Entity(name = "Medico")
@Getter // genera Getters al compilar lombok
@NoArgsConstructor //genera constructor default lombok
@AllArgsConstructor //genera constructor con todos los argumentos
@EqualsAndHashCode(of ="id") //usa parametro id para la comparacion, en este caso de medicos
public class Medico {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded //
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.telefono =datosRegistroMedico.telefono();
        this.documento = datosRegistroMedico.documento();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());


    }
}
