package med.voll.api.domain.medico;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //anotación para cuando queremos hacer test que necesitan de la capa de persistencia
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //utilizar la misma bd(Mysql)
@ActiveProfiles("test")//para que busque las propierties correctas
class MedicoRepositoryTest {

    @Test
    void elegirMedicoAleatorioDisponibleEnLafecha() {
    }
}