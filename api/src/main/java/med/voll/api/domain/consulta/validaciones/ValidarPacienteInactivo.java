package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.cliente.ClienteRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class ValidarPacienteInactivo implements  ValidadorDeConsultas{

    @Autowired
    private ClienteRepository repository;

    public void validar(DatosReservaConsulta datos){
        var  pacienteEstaActivo = repository.findActivoById(datos.idCliente());
        if (!pacienteEstaActivo){
            throw new ValidacionException("No se puede reservar Paciente inactivo");

        }

    }
}
