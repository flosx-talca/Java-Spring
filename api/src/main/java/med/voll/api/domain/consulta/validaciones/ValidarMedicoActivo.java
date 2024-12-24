package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.cliente.ClienteRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoActivo implements  ValidadorDeConsultas{

    @Autowired // con el @Component ya se puede utilizar el @Autowired
    private MedicoRepository repository;

    public void validar(DatosReservaConsulta datos){
        if(datos.idMedico() == null){
            return;
        }

        var  medicoEstaActivo = repository.findActivoById(datos.idMedico());
        if (!medicoEstaActivo){
            throw new ValidacionException("No se puede reservar Medico inactivo");

        }

    }
}

