import org.junit.jupiter.api.Test;

public class GobiernoTest {

    public static void main(String[] args) {
        Documento doc = new Documento("papa", 1);
        Gobierno diputado = new Diputado();
        Gobierno ministro = new Ministro();
        Gobierno presidente = new Presidente();


        diputado.setSiguiente(ministro);
        ministro.setSiguiente(presidente);

        diputado.leerDocumento(doc);

        Documento doc2 = new Documento("Lavado  de dinero", 2);

        diputado.leerDocumento(doc2);


        Documento doc3 = new Documento("Droga", 3);

        diputado.leerDocumento(doc3);
    }
}
