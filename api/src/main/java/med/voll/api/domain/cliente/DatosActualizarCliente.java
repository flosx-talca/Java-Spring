package med.voll.api.domain.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.direccion.Direccion;

public record DatosActualizarCliente(
        @NotNull
        Long id,
        String nombre,
        String edad,
        DatosDireccion direccion
) {

}
