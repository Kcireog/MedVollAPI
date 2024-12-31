package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosReservaConsulta(
        Long idMedico, //puede ser null, porque podemos seleccionar un medico random
        @NotNull
        Long idPaciente,
        @NotNull
        @Future //no puede ser una fecha anterior a la actual
        LocalDateTime fecha
) {
}
