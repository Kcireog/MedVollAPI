package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;

public class ValidadorPacienteConOtraConsultaEnElDia {

    private ConsultaRepository repository;

    public void validar(DatosReservaConsulta datos){

        var primerHorario = datos.fecha().withHour(7); //7am
        var ultimoHorario = datos.fecha().withHour(18);// 6pm

        var pacienteTieneOtraConsultaEnElDia = repository.existsByPacienteIdAndFechaBetween(datos.idPaciente(),primerHorario,ultimoHorario);
        if(pacienteTieneOtraConsultaEnElDia){
            throw new ValidacionException("Paciente ya tiene una consulta reservada para ese día");
        }
    }
}
