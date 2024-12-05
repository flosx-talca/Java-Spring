package med.voll.api.domain.cliente;

import med.voll.api.domain.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findByActivoTrue(Pageable paginacion);
     //List<Cliente> findByActivoTrue();
}
