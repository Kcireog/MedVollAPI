package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.direccion.Direccion;

@Entity(name = "Medico")
@Table(name = "medicos")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String email;

    private String telefono;

    private String documento;

    private Boolean activo;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosMedico) {
        this.nombre = datosMedico.nombre();
        this.email = datosMedico.email();
        this.telefono = datosMedico.telefono();
        this.documento = datosMedico.documento();
        this.activo = true; //el medico que se cree quedara activo
        this.especialidad = datosMedico.especialidad();
        this.direccion = new Direccion(datosMedico.direccion());
    }

    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        if (datosActualizarMedico.nombre() != null) {
            this.nombre = datosActualizarMedico.nombre();
        }
        if (datosActualizarMedico.documento() != null) {
            this.documento = datosActualizarMedico.documento();
        }
        if (datosActualizarMedico.direccion() != null) {
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
        }

    }

    public void desactivarMedico(){
        this.activo = false;
    }

}
