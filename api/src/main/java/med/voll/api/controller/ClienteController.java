package med.voll.api.controller;


import med.voll.api.domain.cliente.Cliente;
import med.voll.api.domain.cliente.ClienteRepository;
import med.voll.api.domain.cliente.DatoListadoCliente;
import med.voll.api.domain.cliente.DatosRegistroCliente;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public void registrarCliente(@RequestBody DatosRegistroCliente datosRegistroCliente){
        clienteRepository.save(new Cliente(datosRegistroCliente));

    }

    @GetMapping
    public List<DatoListadoCliente>   listadoCliente(){
        List<Cliente> cliente = clienteRepository.findAll();
        return (cliente.stream().map(DatoListadoCliente::new).toList());


    }

}
