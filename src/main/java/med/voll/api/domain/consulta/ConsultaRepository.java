package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {


    //Query Method, funcionalidad de Spring Data JPA
    boolean existsByPacienteIdAndFechaBetween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    //a aplicación debe permitir la reserva de una consulta en una fecha en la que otra consulta fue reservada,
    // pero cancelada posteriormente.
    boolean existsByMedicoIdAndFechaAndMotivoCancelamientoIsNull(Long idMedico, LocalDateTime fecha);
}
