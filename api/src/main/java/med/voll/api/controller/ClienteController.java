package med.voll.api.controller;


import med.voll.api.domain.cliente.Cliente;
import med.voll.api.domain.cliente.ClienteRepository;
import med.voll.api.domain.cliente.DatosRegistroCliente;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public void registrarCliente(@RequestBody DatosRegistroCliente datosRegistroCliente){
        clienteRepository.save(new Cliente(datosRegistroCliente));

    }

}
