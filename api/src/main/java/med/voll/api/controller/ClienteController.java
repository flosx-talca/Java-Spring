package med.voll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.cliente.*;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.DatosListadoMedico;
import med.voll.api.domain.medico.DatosRespuestaMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/cliente")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    //devuelve 201
    @PostMapping
    public ResponseEntity<DatosRespuestaCliente> registrarCliente(@RequestBody @Valid DatosRegistroCliente datosRegistroCliente, UriComponentsBuilder uriComponentsBuilder){
       // Medico medico =  medicoRepository.save(new Medico(datosRegistroMedico));
        //clienteRepository.save(new Cliente(datosRegistroCliente));
        Cliente cliente = clienteRepository.save(new Cliente(datosRegistroCliente));
        DatosRespuestaCliente datosRespuestaCliente = new DatosRespuestaCliente(cliente.getId(), cliente.getNombre(), cliente.getDocumento(), cliente.getEmail(), cliente.getEdad(),
                 new DatosDireccion( cliente.getDireccion().getCalle(), cliente.getDireccion().getComplemento(), cliente.getDireccion().getCiudad(), cliente.getDireccion().getNumero(),
        cliente.getDireccion().getDistrito()));
        URI url = uriComponentsBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCliente);


    }
    //RETORNA 200
    @GetMapping
    public ResponseEntity <Page<DatoListadoCliente>>   listadoCliente(@PageableDefault(size = 2) Pageable pagina){

        //List<Cliente> cliente = clienteRepository.findAll();
        //List<Cliente> cliente = clienteRepository.findByActivoTrue();
        //return (cliente.stream().map(DatoListadoCliente::new).toList());

        //return clienteRepository.findByActivoTrue(pagina).map(DatoListadoCliente::new);
        return ResponseEntity.ok(clienteRepository.findByActivoTrue(pagina).map(DatoListadoCliente::new));


    }

    //RETORNA 200
    @PutMapping
    @Transactional
    public ResponseEntity modificarCliente(@RequestBody @Valid DatosActualizarCliente datosActualizarCliente){
        Cliente cliente = clienteRepository.getReferenceById(datosActualizarCliente.id());
        cliente.actualizarDatos(datosActualizarCliente);
        //clienteRepository.save(cliente);
        return ResponseEntity.ok(new DatosRespuestaCliente(cliente.getId(), cliente.getNombre(), cliente.getDocumento(), cliente.getEmail(), cliente.getEdad(),
                new DatosDireccion( cliente.getDireccion().getCalle(), cliente.getDireccion().getComplemento(), cliente.getDireccion().getCiudad(), cliente.getDireccion().getNumero(),
                cliente.getDireccion().getDistrito())));




    }
    //RETORNA 204
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarCliente(@PathVariable Long id){
        Cliente cliente = clienteRepository.getReferenceById(id);
        cliente.desactivarCliente();
        return ResponseEntity.noContent().build();


    }

    //RESPONDE 200
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCliente> retonarDatosCliente(@PathVariable Long id){ // Se modifica declaracion del metodo agregano ResponEntity que es para devolver codigo se operacion validas en este caso 204
        Cliente cliente = clienteRepository.getReferenceById(id);
        var datosCliente =  new DatosRespuestaCliente(cliente.getId(), cliente.getNombre(), cliente.getDocumento(), cliente.getEmail(), cliente.getEdad(),
                new DatosDireccion( cliente.getDireccion().getCalle(), cliente.getDireccion().getComplemento(), cliente.getDireccion().getCiudad(), cliente.getDireccion().getNumero(),
                        cliente.getDireccion().getDistrito()));
        return ResponseEntity.ok(datosCliente);
    }

}
