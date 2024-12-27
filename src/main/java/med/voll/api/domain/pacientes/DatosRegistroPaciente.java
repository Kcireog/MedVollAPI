package med.voll.api.domain.pacientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroPaciente(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String documentoIdentidad,

        @NotBlank
        String telefono,

        @NotNull // los objetos no pueden llegar blancos, solo podrian llegar nulos
        @Valid  //validar que los datos recibidos cumplan con los parametros de establecidos
        DatosDireccion direccion
) {
}
