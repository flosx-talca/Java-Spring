package med.voll.api.domain.medico;

public record DatosListadoMedico(
        Long id,
        String nombre,
        String especialidad,
        String documento,
        String email

) {
  public DatosListadoMedico(Medico medico){
      this(medico.getId(), medico.getNombre(), medico.getDocumento().toString(), medico.getEmail(), medico.getTelefono());

  }
}



