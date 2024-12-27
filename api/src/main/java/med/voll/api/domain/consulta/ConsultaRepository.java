package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository  extends JpaRepository<Consulta, Long> {



    boolean existsByClienteIdAndFechaBetween(@NotNull Long id, LocalDateTime primerHorario, LocalDateTime ultimoHorario);
   /* @Query("""
            Select c.id 
            From consultas c
            Where 
            c.medico_Id = :id
            and
            c.fecha = :fecha 
            
            """)*/

    boolean existsByMedicoIdAndFechaAndMotivoCancelamientoIsNull(Long idMedico, LocalDateTime fecha);
    //boolean existsByMedicoIdAndFecha(Long idMedico, @NotNull @Future LocalDateTime fecha);
}
