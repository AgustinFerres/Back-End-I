import dao.BD;
import models.Odontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

public class Cliente {

    @Test
    public void casoPrueba () {

        BD.crearTablas();
        OdontologoService odontologoService = new OdontologoService();
        Odontologo odontologo1 = new Odontologo(1234,"Agustin","Ferres");

        odontologoService.guardar(odontologo1);

        String resActual = odontologoService.buscar(1).toString();
        String resEsperadal = "Agustin Ferres matricula: 1234";

        Assertions.assertEquals(resEsperadal,resActual);
    }
}
