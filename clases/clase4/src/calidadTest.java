import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class calidadTest {

    @Test
    public void casoAceptado(){

        Articulo articulo = new Articulo("papa", 1100, 1250, "sano");

        CompruebaCalidad compruebaCalidad = new CompruebaCalidad();

        String resEsperada = "aceptado";
        String resAcual = compruebaCalidad.checkArticulo(articulo);

        Assertions.assertEquals(resEsperada,resAcual);
    }
    @Test
    public void casoRechazado(){

        Articulo articulo = new Articulo("papa", 1250, 1250, "normal");

        CompruebaCalidad compruebaCalidad = new CompruebaCalidad();

        String resEsperada = "rechazado";
        String resAcual = compruebaCalidad.checkArticulo(articulo);

        Assertions.assertEquals(resEsperada,resAcual);
    }
}
