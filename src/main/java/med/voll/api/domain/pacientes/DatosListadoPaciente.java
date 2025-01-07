package med.voll.api.domain.pacientes;

public record DatosListadoPaciente(
        Long id,
        String nombre,
        String email,
        String documentoIdentidad
) {
    //para obtener solos los datos que quiero mostrar del medico en la bd
    public DatosListadoPaciente(Paciente paciente) {
        this(paciente.getId(),paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoIdentidad());
    }
}
