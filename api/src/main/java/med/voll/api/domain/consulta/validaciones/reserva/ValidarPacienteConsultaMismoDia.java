package med.voll.api.domain.consulta.validaciones.reserva;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteConsultaMismoDia implements  ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository repository;

    public void validar (DatosReservaConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);

        var pacienteTieneOtraConsultaEnElDia = repository.existsByClienteIdAndFechaBetween(datos.idCliente(), primerHorario,ultimoHorario);
        if(pacienteTieneOtraConsultaEnElDia){
            throw new ValidacionException("EL paciente ya tiene hora en el mismo dia");

        }

    }

}
