package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosReservaConsulta(
        Long idMedico, //puede ser null, porque se asignara un medico random, en caso de que el id no exista
        @NotNull
        Long idPaciente,
        @NotNull
        @Future //no puede ser una fecha anterior a la actual
        LocalDateTime fecha,
        Especialidad especialidad //esta se dara en caso de no saber con que medico quiere la cita
) {
}
