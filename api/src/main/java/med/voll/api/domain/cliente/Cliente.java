package med.voll.api.domain.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.direccion.Direccion;
import med.voll.api.domain.medico.DatosActualizarMedico;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity (name="Cliente")
@Table(name="clientes")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of="id")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nombre;
    private String documento;
    private String email;
    private String edad;
    @Embedded
    private Direccion direccion;
    boolean activo = true;

    public Cliente(DatosRegistroCliente datosRegistroCliente){
        this.nombre = datosRegistroCliente.nombre();
        this.documento = datosRegistroCliente.documento();
        this.email = datosRegistroCliente.email();
        this.edad = datosRegistroCliente.edad();
        this.direccion = new Direccion(datosRegistroCliente.direccion());
        this.activo = true;
    }

    public void actualizarDatos(DatosActualizarCliente datosActualizarCliente){
        if (datosActualizarCliente.nombre()!=null){
            this.nombre = datosActualizarCliente.nombre();
        }
        if(datosActualizarCliente.edad()!=null){
            this.edad = datosActualizarCliente.edad();

        }

        if (datosActualizarCliente.direccion() != null){
            this.direccion = direccion.actualizarDatos(datosActualizarCliente.direccion());
        }

    }

    public void desactivarCliente() {
        this.activo=false;
    }

}