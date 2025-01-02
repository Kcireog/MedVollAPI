package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query(value = """
            SELECT m from Medico m
            where m.activo = 1 
            and m.especialidad = :especialidad
            and m.id not in(
                select c.medico.id from Consulta c
                where c.fecha = :fecha
            
            )
            order by  rand()
            limit 1
            """, nativeQuery = true)
//para que acepte el 1 en activo where m.activo = true, en caso de que no tenga native
    Medico elegirMedicoAleatorioDisponibleEnLafecha(Especialidad especialidad, @NotNull @Future LocalDateTime fecha);
}
