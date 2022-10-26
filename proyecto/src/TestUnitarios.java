import entidadesNegocio.Clinica;
import entidadesNegocio.Odontologo;
import entidadesNegocio.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestUnitarios {

    @Test
    // CASO POSITIVO
    public void hacerReserva(){
        Paciente gonza = new Paciente("Gonza", "Alvarez", "Cerca de 18", "5310543-1", LocalDate.of(2022,10,26));
        Odontologo agus = new Odontologo("Agus", "Carnicero", "HE11");

        Clinica clinica = new Clinica();

        clinica.agregarPaciente(gonza);
        clinica.agregarOdontologo(agus);

        gonza.hacerReserva(clinica,agus,LocalDate.of(2022,10,27), LocalTime.of(11,0));
        Assertions.assertEquals(1,clinica.getTurnos().size());
    }
    @Test
    // CASO NGATIVO
    public void hacerReservaFallado(){
        Paciente gonza = new Paciente("Gonza", "Alvarez", "Cerca de 18", "5310543-1", LocalDate.of(2022,10,26));
        Odontologo agus = new Odontologo("Agus", "Carnicero", "HE11");

        Clinica clinica = new Clinica();
        // Paciente no esta registrado en la clinica
        gonza.hacerReserva(clinica,agus,LocalDate.of(2022,10,27), LocalTime.of(11,0));
        Assertions.assertEquals(0,clinica.getTurnos().size());
    }
}
