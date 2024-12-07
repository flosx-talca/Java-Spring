package med.voll.api.domain.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;
import org.hibernate.validator.constraints.Length;

public record DatosRespuestaCliente(
        Long id,
        String nombre,
        String documento,
        String email,
        String edad,
        DatosDireccion direccion
) {


}
