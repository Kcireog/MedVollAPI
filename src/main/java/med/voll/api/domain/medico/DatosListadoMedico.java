package med.voll.api.domain.medico;

public record DatosListadoMedico(
        Long id,
        String nombre,
        String especialidad,
        String documento,
        String email

) {
    //para obtener solos los datos que quiero mostrar del medico en la bd
    public DatosListadoMedico(Medico medico){
        this(medico.getId(),medico.getNombre(),medico.getEspecialidad().toString(),medico.getDocumento(),medico.getEmail());
    }
}
