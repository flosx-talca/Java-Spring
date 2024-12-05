package med.voll.api.domain.cliente;

import jakarta.validation.constraints.*;
import med.voll.api.domain.direccion.DatosDireccion;
import org.hibernate.validator.constraints.Length;

public record DatosRegistroCliente(
        @NotBlank
        String nombre,

        @NotBlank
        @Pattern(regexp = "\\d{4,10}") //numero digito entre 4 y 10 digitos
        String documento,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Length(max=3, min=1)
        String edad,

        @NotNull
        DatosDireccion direccion

) {

}
