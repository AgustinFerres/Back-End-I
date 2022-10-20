import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DescargaTest {

    @Test
    public void casPositivo() {
        Usuario usuario = new Usuario(1,"Premium");

        DescargaSpotify descargaSpotify = new ProxySpotify();
        usuario.solicitarCancion("Night");

        String resEsperada = "Se esta descargando Night. \n Por favor espere.";
        String resActual = descargaSpotify.descargar(usuario);

        Assertions.assertEquals(resEsperada,resActual);
    }
    @Test
    public void casoNegativo() {
        Usuario usuario = new Usuario(1,"Free");

        DescargaSpotify descargaSpotify = new ProxySpotify();
        usuario.solicitarCancion("Night");

        String resEsperada = "Necesitas premium para poder descargar canciones.";
        String resActual = descargaSpotify.descargar(usuario);

        Assertions.assertEquals(resEsperada,resActual);
    }
}
