package med.voll.api.domain.consulta.validaciones.reserva;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoConOtraConsultaMismoDia implements  ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository repository;

    public void  validar (DatosReservaConsulta datos){
        var medicoTieneOtraConsultaEnElDia = repository.existsByMedicoIdAndFechaAndMotivoCancelamientoIsNull(datos.idMedico(), datos.fecha());
       // var test =  repository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());
        if(medicoTieneOtraConsultaEnElDia){
            throw new ValidacionException("Medico tiene hora el mismo dia y hora");
        }


    }
}
