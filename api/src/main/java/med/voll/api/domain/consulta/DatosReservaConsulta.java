package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosReservaConsulta(

        Long idMedico,

        @NotNull
        Long idCliente,

        @NotNull
        @Future
        LocalDateTime fecha,
        //@JsonFormat(pattern = "yyyy-MM-ddTHH:mm") //le indicamos a spring que queremos la fecha a lo chileno


        Especialidad especialidad



) {
}
