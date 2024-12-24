package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;



@Component
public class ValidarFueraHorarioConsultas implements  ValidadorDeConsultas {

    public void validar(DatosReservaConsulta datos){

        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeAperturaClinica = fechaConsulta.getHour()<7;
        var horarioDespuesDeCierraClinica = fechaConsulta.getHour()>18;

        if (domingo || horarioAntesDeAperturaClinica || horarioDespuesDeCierraClinica){
            throw new ValidacionException("Horario no valido para generar consulta");
        }



    }

}
