package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico, UriComponentsBuilder uriComponentsBuilder){

       Medico medico =  medicoRepository.save(new Medico(datosRegistroMedico));
       DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(),medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getEspecialidad().toString(),
               new DatosDireccion(medico.getDireccion().getCalle(),medico.getDireccion().getNumero(),medico.getDireccion().getCiudad(), medico.getDireccion().getComplemento(),
                       medico.getDireccion().getDistrito()));
        //return 201 Created
        //URL donde encontrar medico
        //http://localhost:8080/medico/xx

        URI url = uriComponentsBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }



    //@Pageable default define el tamaño y paginacion
    @GetMapping
    public ResponseEntity <Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 2) Pageable pagina){
        //return medicoRepository.findAll(pagina).map(DatosListadoMedico::new);
        //return medicoRepository.findByActivoTrue(pagina).map(DatosListadoMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(pagina).map(DatosListadoMedico::new));
    }



    @PutMapping
    @Transactional // es para cerrar la transsaccion no se esta ocupando un save, JPA se preocupa de que la actualizacion sea correcta para hacer commit;
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(),medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getEspecialidad().toString(),
        new DatosDireccion(medico.getDireccion().getCalle(),medico.getDireccion().getNumero(),medico.getDireccion().getCiudad(), medico.getDireccion().getComplemento(),
                medico.getDireccion().getDistrito())));


    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar_medico(@PathVariable Long id){ // Se modifica declaracion del metodo agregano ResponEntity que es para devolver codigo se operacion validas en este caso 204
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    /*
    public void eliminarMedico(@PathVariable Long id){
        //System.out.println(id);
        Medico medico = medicoRepository.getReferenceById(id);

        medicoRepository.delete(medico);


    }*/


    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retonarDatosMedico(@PathVariable Long id){ // Se modifica declaracion del metodo agregano ResponEntity que es para devolver codigo se operacion validas en este caso 204
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedico = new DatosRespuestaMedico(medico.getId(),medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(),medico.getDireccion().getNumero(),medico.getDireccion().getCiudad(), medico.getDireccion().getComplemento(),
                        medico.getDireccion().getDistrito()));
        return ResponseEntity.ok(datosMedico);
    }





}
