package med.voll.api.domain.pacientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosActualizacionPaciente(

        @NotNull
        Long id,
        String nombre,
        @Email
        String email,
        String telefono,
        String documentoIdentidad,
        @Valid
        DatosDireccion direccion
) {
}
