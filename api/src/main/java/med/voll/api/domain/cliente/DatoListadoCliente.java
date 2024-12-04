package med.voll.api.domain.cliente;

import med.voll.api.domain.medico.Medico;

public record DatoListadoCliente(
        String nombre,
        String edad
) {

    public DatoListadoCliente(Cliente cliente){
        this(cliente.getNombre(), cliente.getEdad());

    }


}
