package med.voll.api.domain.cliente;

import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroCliente(
        String nombre,
        String documento,
        String email,
        String edad,
        DatosDireccion direccion

) {

}
