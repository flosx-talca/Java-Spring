package med.voll.api.domain.consulta;

import java.time.LocalDateTime;


public record DatosDetalleConsulta(
        Long id,

        Long idMedico,

        Long idCliente,

        LocalDateTime fecha
){
    public DatosDetalleConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getCliente().getId(), consulta.getFecha());
    }
}
