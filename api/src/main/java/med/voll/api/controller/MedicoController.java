package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico){

        medicoRepository.save(new Medico(datosRegistroMedico));
    }

    //@Pageable default define el tamaño y paginacion
    @GetMapping
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size = 2) Pageable pagina){
        //return medicoRepository.findAll(pagina).map(DatosListadoMedico::new);
        return medicoRepository.findByActivoTrue(pagina).map(DatosListadoMedico::new);
    }

    @PutMapping
    @Transactional // es para cerrar la transsaccion no se esta ocupando un save, JPA se preocupa de que la actualizacion sea correcta para hacer commit;
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar_medico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();


    }

    /*
    public void eliminarMedico(@PathVariable Long id){
        //System.out.println(id);
        Medico medico = medicoRepository.getReferenceById(id);

        medicoRepository.delete(medico);


    }*/




}
