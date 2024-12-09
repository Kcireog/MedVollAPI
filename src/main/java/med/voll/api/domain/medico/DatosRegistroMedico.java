package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroMedico(
        @NotBlank //hace lo mismo que NotNull, entonces estaria validando que no llegue ni nulo ni blanco
        String nombre,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefono,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")//expresión regular
        String documento,

        @NotNull //porque es un objeto
        Especialidad especialidad,

        @NotNull // los objetos no pueden llegar blancos, solo podrian llegar nulos
        @Valid  //validar que los datos recibidos cumplan con los parametros de establecidos
        DatosDireccion direccion
) {
}
