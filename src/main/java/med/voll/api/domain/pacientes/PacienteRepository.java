package med.voll.api.domain.pacientes;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByActivoTrue(Pageable paginacion);


    boolean findActivoById(Long idPaciente);
}
