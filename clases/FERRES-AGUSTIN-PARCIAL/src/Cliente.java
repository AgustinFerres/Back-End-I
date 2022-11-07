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
        Odontologo odontologo2 = new Odontologo(1134,"Brahian","Tierno");
        Odontologo odontologo3 = new Odontologo(2312,"Gonzalo","Alvarez");
        Odontologo odontologo4 = new Odontologo(3212,"Rodrigo","Benitez");
        Odontologo odontologo5 = new Odontologo(4212,"Javier","Paredes");


        odontologoService.guardar(odontologo1);
        odontologoService.guardar(odontologo2);
        odontologoService.guardar(odontologo3);
        odontologoService.guardar(odontologo4);
        odontologoService.guardar(odontologo5);

        Integer resActual = odontologoService.buscarTodos().size();
        Integer resEsperadal = 5;

        Assertions.assertEquals(resEsperadal,resActual);
    }
}
