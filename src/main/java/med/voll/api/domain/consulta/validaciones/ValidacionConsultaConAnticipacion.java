package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidacionConsultaConAnticipacion {

    public void validar(DatosReservaConsulta datos){
        var fechaConsulta = datos.fecha();
        var ahora = LocalDateTime.now();//horario de ahora
        var diferenciaMinutos = Duration.between(fechaConsulta, ahora).toMinutes();

        if(diferenciaMinutos < 30){
            throw new ValidacionException("Horario seleccionado menor que 30min de anticipación");
        }

    }
}
