package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.DatosListadoMedico;
import med.voll.api.domain.pacientes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public Page<DatosListadoPaciente> listar(@PageableDefault(page = 0, size = 10, sort = {"nombre"}) Pageable paginacion) {
        return pacienteRepository.findAll(paginacion).map(DatosListadoPaciente::new);
    }

    @PostMapping
    @Transactional
    public void registrar(DatosRegistroPaciente datos) {
        pacienteRepository.save(new Paciente(datos));
    }

    @PutMapping
    @Transactional
    public void actualizar(@RequestBody @Valid DatosActualizacionPaciente datos) {
        var paciente = pacienteRepository.getReferenceById(datos.id());
        paciente.atualizarInformacion(datos);

    }

    //delete logico
    public void eliminar(Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.inactivar();
    }
}
