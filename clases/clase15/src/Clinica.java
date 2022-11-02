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
    public void casoGuardar (){
        PacienteService pacienteService = new PacienteService();

        BD.crearTablas();
        Domicilio domicilio = new Domicilio("San Martin", 2312, "Cordoba", "Cordoba");
        Paciente paciente = new Paciente("Paredes", "Javier", "35885841", LocalDate.of(2022,10,9), domicilio);

        pacienteService.guardar(paciente);

        String respActual = pacienteService.buscar(1).toString();
        String respEsperada = "Javier Paredes\n" +
                "DNI: 35885841 Alta: 2022-10-09\n" +
                "Vive en: Cordoba, Cordoba, San Martin 2312";

        Assertions.assertEquals(respEsperada,respActual);

    }
    @Test
    public void casoActualizar (){
        PacienteService pacienteService = new PacienteService();

        Domicilio domicilio = new Domicilio(1,"Artigas", 2312, "Rivera", "Rivera");
        Paciente paciente = new Paciente(1,"Alvarez", "Gonzalo", "35885841", LocalDate.of(2022,10,9), domicilio);

        pacienteService.actualizar(paciente);

        String respActual = pacienteService.buscar(1).toString();
        String respEsperada = "Gonzalo Alvarez\n" +
                "DNI: 35885841 Alta: 2022-10-09\n" +
                "Vive en: Rivera, Rivera, Artigas 2312";

        Assertions.assertEquals(respEsperada,respActual);
    }
    @Test
    public void casoEliminar (){
        PacienteService pacienteService = new PacienteService();

        Domicilio domicilio = new Domicilio("Artigas", 2312, "Rivera", "Rivera");
        Paciente paciente = new Paciente("Alvarez", "Gonzalo", "35885841", LocalDate.of(2022,10,9), domicilio);

        Domicilio domicilio2 = new Domicilio("San Martin", 2312, "Cordoba", "Cordoba");
        Paciente paciente2 = new Paciente("Paredes", "Javier", "35885841", LocalDate.of(2022,10,9), domicilio2);

        pacienteService.guardar(paciente);
        pacienteService.guardar(paciente2);

        pacienteService.eliminar(1);

        Integer respActual = pacienteService.buscarTodos().size();
        Integer respEsperada = 2;

        Assertions.assertEquals(respEsperada,respActual);
    }
}
