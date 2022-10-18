import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FiguraGeometricaTest {
    @Test
    public void casoCuadrado(){
        FiguraGeometrica figuraPrueba = new Cuadrado(5.0);

        String respEsperada = "El area del Cuadrado es de 25.0";
        try {
            String area = figuraPrueba.calcularArea();
            Assertions.assertEquals(respEsperada,area);
        } catch (FiguraGeometricaException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void casoCirculo(){
        FiguraGeometrica figuraPrueba = new Circulo(3.0);

        String respEsperada = "El area del Circulo es de 28.274333882308138";
        try {
            String area = figuraPrueba.calcularArea();
            Assertions.assertEquals(respEsperada,area);
        } catch (FiguraGeometricaException e) {
            e.printStackTrace();
        }
    }
}