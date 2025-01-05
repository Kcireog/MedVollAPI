package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorFueraHorarioConsultas implements ValidadorDeConsultas {
    public void validar(DatosReservaConsulta datos){
        /*
        El horario de atención de la clinica es de l-s de 7 a 19 hrs
        Y las consultas tienen una duración fija de 1hr
         */
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeAperturaClinica = fechaConsulta.getHour() < 7;//7am
        var horarioDespuesDeCierreClinica = fechaConsulta.getHour() > 18;// 6pm

        if(domingo || horarioAntesDeAperturaClinica || horarioDespuesDeCierreClinica){
            throw new ValidacionException("Horario seleccionado fuera del horario de atención.");
        }

    }
}
