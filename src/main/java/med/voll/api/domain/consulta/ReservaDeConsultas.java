package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    public void reservar(DatosReservaConsulta datos) {

        if(!pacienteRepository.existsById(datos.idPaciente())){
            throw new ValidacionException("No existe un paciente con el id informado...");
        }
        if(datos.idMedico()!= null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionException("No existe un medico con el id informado...");
        }
        var medico = elegirMedico(datos);
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();//el get para evitar obtener un Optional
        var consulta = new Consulta(null, medico, paciente, datos.fecha());

        consultaRepository.save(consulta);
    }

    private Medico elegirMedico(DatosReservaConsulta datos) {


    }
}
