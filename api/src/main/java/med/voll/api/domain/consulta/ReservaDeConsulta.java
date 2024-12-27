package med.voll.api.domain.consulta;

import jakarta.validation.Valid;
import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.cliente.ClienteRepository;
import med.voll.api.domain.consulta.validaciones.cancelacion.ValidadorCancelamientoDeConsulta;
import med.voll.api.domain.consulta.validaciones.reserva.ValidadorDeConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
//
      @Autowired
    private List<ValidadorDeConsultas> validadores;

    @Autowired
    private List<ValidadorCancelamientoDeConsulta> validadoresCancelamiento;





        public DatosDetalleConsulta reservar(DatosReservaConsulta datos){


            if(!clienteRepository.existsById(datos.idCliente())){
                throw new ValidacionException("No existe Cliente con el id informado");
            }
            System.out.println(clienteRepository.existsById(datos.idCliente()));
            if( datos.idMedico()!= null && !medicoRepository.existsById(datos.idMedico())){
                throw new ValidacionException("No existe Medico con el id informado");
            }



            //Patron Strategy

            // PRICIPIO SOLID, en este caSO S O y D

            validadores.forEach(v -> v.validar(datos));


            //var medico = medicoRepository.findById(datos.idMedico()).get();
            var medico = elegirMedico(datos);

            if( medico == null){
                throw new ValidacionException("No existe Medico Disponible en ese horario");
            }

            var cliente = clienteRepository.findById(datos.idCliente()).get();

            var consulta = new Consulta(null,  medico, cliente, datos.fecha(), null);

            consultaRepository.save(consulta);
            return new DatosDetalleConsulta(consulta);





        }

        private  Medico elegirMedico(DatosReservaConsulta datos) {
                if(datos.idMedico() != null ){
                    return medicoRepository.getReferenceById(datos.idMedico());

                }
                if (datos.especialidad() == null){

                    throw new ValidacionException("Es necesario elegir una especialidad");

                }
            return medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(datos.especialidad(), datos.fecha());
        }


    public void cancelar(@Valid DatosCancelamientoConsulta datos) {

            if( !consultaRepository.existsById(datos.idConsulta())){
                 throw new ValidacionException("ID de consulta no existe");

            }
            validadoresCancelamiento.forEach(v -> v.validar(datos));

            var consulta = consultaRepository.getReferenceById(datos.idConsulta());
            consulta.cancelar(datos.motivo());

    }


}

   
