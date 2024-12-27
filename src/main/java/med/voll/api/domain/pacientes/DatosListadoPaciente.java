package med.voll.api.domain.pacientes;

public record DatosListadoPaciente(
        String nombre,
        String email,
        String documentoIdentidad
) {
    //para obtener solos los datos que quiero mostrar del medico en la bd
    public DatosListadoPaciente(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoIdentidad());
    }
}
