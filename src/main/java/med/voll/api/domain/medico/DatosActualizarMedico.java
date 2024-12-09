package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosActualizarMedico(
        @NotNull //no utilizo el notbland, porque es para los strings
        Long id,
        String nombre,
        String documento,
        DatosDireccion direccion
) {
}
