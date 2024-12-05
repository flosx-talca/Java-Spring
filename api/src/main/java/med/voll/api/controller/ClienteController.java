package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.domain.cliente.*;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public void registrarCliente(@RequestBody @Valid DatosRegistroCliente datosRegistroCliente){
        clienteRepository.save(new Cliente(datosRegistroCliente));

    }

    @GetMapping
    public Page<DatoListadoCliente>   listadoCliente(Pageable pagina){

        //List<Cliente> cliente = clienteRepository.findAll();
        //List<Cliente> cliente = clienteRepository.findByActivoTrue();
        //return (cliente.stream().map(DatoListadoCliente::new).toList());

        return clienteRepository.findByActivoTrue(pagina).map(DatoListadoCliente::new);


    }

    @PutMapping
    public void modificarCliente(@RequestBody DatosActualizarCliente datosActualizarCliente){
        Cliente cliente = clienteRepository.getReferenceById(datosActualizarCliente.id());
        cliente.actualizarDatos(datosActualizarCliente);
        clienteRepository.save(cliente);


    }

    @DeleteMapping("/{id}")

    public void borrarCliente(@PathVariable Long id){
        Cliente cliente = clienteRepository.getReferenceById(id);
        cliente.desactivarCliente();
        clienteRepository.save(cliente);


    }

}
