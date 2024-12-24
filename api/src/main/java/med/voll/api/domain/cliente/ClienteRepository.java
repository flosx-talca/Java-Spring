package med.voll.api.domain.cliente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findByActivoTrue(Pageable paginacion);


    /*@Query("""
            Select c.activo
            From cliente c
            Where
            c.ClienteId = :idCliente
            """)*/


    boolean findActivoById(@NotNull Long idCliente);
    //List<Cliente> findByActivoTrue();

}

