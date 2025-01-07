package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoActivo implements ValidadorDeConsultas {
    /*
    no se utiliza @Autowired directamente en la declaración del atributo
    del repositorio. Esto se debe a que estas clases de validación,
    en este ejemplo, no son beans de Spring. No están anotadas con
    @Component, @Service, @Repository, etc., que son las anotaciones que
    le indican a Spring que debe gestionar su creación e inyección de dependencias.
     */
    /*
    Para que @Autowired funcione, la clase debe ser un bean administrado por Spring.
    Si intentáramos inyectar el repositorio directamente sin hacer la clase un bean,
    Spring no sabría dónde inyectar la dependencia.
     */

    @Autowired //como ya se reconoce la clase, podemos inyectar
    private MedicoRepository repository;

    public void validar(DatosReservaConsulta datos) {
        //elección del medíco opcional
        if (datos.idMedico() == null) {
            return;
        }
        var medicoEstaActivo = repository.findActivoById(datos.idMedico());
        if (!medicoEstaActivo) {
            throw new ValidacionException("Consulta no puede ser reservada con medico excluido");
        }
    }
}
