package med.voll.api.medico;

import med.voll.api.direccion.DatosDireccion;

public record DatosRegistroMedico(
        String medico,
        String email,
        String documento,
        Especialidad especialidad,
        DatosDireccion direccion

) {
}
