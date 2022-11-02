import dao.BD;
import model.Domicilio;
import model.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.*;

import java.sql.Date;
import java.time.LocalDate;

public class Clinica {

    @Test
    public void caso1 (){
        PacienteService pacienteService = new PacienteService();

        BD.crearTablas();
        Domicilio domicilio = new Domicilio("San Martin", 2312, "Cordoba", "Cordoba");
        Paciente paciente = new Paciente("Paredes", "Javier", "35885841", new Date(2022 - 1900,9,9), domicilio);
        pacienteService.guardar(paciente);
        String respActual = pacienteService.buscar(1).toString();
        String respEsperada = "Javier Paredes\n" +
                "DNI: 35885841 Alta: 2022-10-09\n" +
                "Vive en: Cordoba, Cordoba, San Martin 2312";
        Assertions.assertEquals(respEsperada,respActual);

    }
}
