package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.validaciones.cancelamiento.ValidadorCancelamientoConsulta;
import med.voll.api.domain.consulta.validaciones.reserva.ValidadorDeConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorDeConsultas> validadores; //reconoce que es una interfaz y buscara todas las clases
    //                                                que la implementen(Los validadores)
    @Autowired
    private List<ValidadorCancelamientoConsulta> validadoresCancelamiento;

    public DatosDetalleConsulta reservar(DatosReservaConsulta datos) {

        if (!pacienteRepository.existsById(datos.idPaciente())) {
            throw new ValidacionException("No existe un paciente con el id informado...");
        }
        if (datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
            throw new ValidacionException("No existe un medico con el id informado...");
        }

        //validaciones
        validadores.forEach(v -> v.validar(datos)); //ejecutara el metodo validar de cada una de los validadores

        var medico = elegirMedico(datos);
        if (medico == null) {
            throw new ValidacionException("No existe un medico disponible en ese horario");
        }

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();//el get para evitar obtener un Optional
        var consulta = new Consulta(null, medico, paciente, datos.fecha(), null);

        consultaRepository.save(consulta);
        //     llamada al constructor
        return new DatosDetalleConsulta(consulta); //creamos un DatosDetalleConsulta,
        //                                           para devolver a la respuesta de la request reservar en controller
    }

    public void cancelar(DatosCancelamientoConsulta datos) {

        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionException("No existe un consulta con el id informado...");
        }
        //validadores
        validadoresCancelamiento.forEach(v -> v.validar(datos));

        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }

    private Medico elegirMedico(DatosReservaConsulta datos) {

        if (datos.idMedico() != null) {
            return medicoRepository.getReferenceById(datos.idMedico());// el reference devuelve el objeto en si
        }
        if (datos.especialidad() == null) {
            throw new ValidacionException("Es necesario elegir una especialidad, cuando no se elige un medico");
        }

        return medicoRepository.elegirMedicoAleatorioDisponibleEnLafecha(datos.especialidad(), datos.fecha());

    }


}
