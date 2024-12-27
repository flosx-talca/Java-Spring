package med.voll.api.domain.consulta.validaciones.reserva;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

//@Component // Se podria Usaar service, pero se utilizara @component para que spring lo reconozca
@Component("ValidadorConsultaConAnticipacionReserva")

public class ValidarConsultaConAnticipacion  implements  ValidadorDeConsultas{

    public void validar(DatosReservaConsulta datos){

     LocalDateTime  fechaConsulta = datos.fecha();

       // System.out.println(fechaConsulta);
       var ahora = LocalDateTime.now();

       var diferenciaEnMinutos = Duration.between(ahora, fechaConsulta).toMinutes();

       // System.out.println("MINUTOS: "+diferenciaEnMinutos);

       if(diferenciaEnMinutos<30){

           throw new ValidacionException("No se puede reservar con menos de 30 minutos de anticipacion");

       }


    }

}
