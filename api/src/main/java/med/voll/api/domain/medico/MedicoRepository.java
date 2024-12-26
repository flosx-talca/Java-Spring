package med.voll.api.domain.medico;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByActivoTrue(Pageable pagiancion);


    @Query("""
            Select m from Medico m
            Where
            m.activo = true
            and
            m.especialidad = :especialidad
            and m.id not in(
                Select c.medico.id From Consulta c
                where
                c.fecha = :fecha)
            
            Order by rand()
            limit 1
            
            """)

    Medico elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad especialidad, @NotNull @Future LocalDateTime fecha);


   @Query("""
            Select m.activo
            From Medico m
            Where
            m.id = :idMedico
            """)
    boolean findActivoById(Long idMedico);
}
