import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChefTest {

    @Test
    public void chefClasico(){

        Menu clasico = new MenuClasico(100.0);
        Chef chefClasico = new ChefClasico();

        String respEsperada = "El menu debe de ser clasico, y sale 100.0";
        String respActual = chefClasico.preparar(clasico);

        Assertions.assertEquals(respEsperada,respActual);
    }
    @Test
    public void chefInf(){

        Menu infantil = new MenuInfantil(100.0, 3);
        Chef chefInfantil = new ChefInfantil();

        String respEsperada = "Es un menu infantil con 3 juguetes, y sale 109.0";
        String respActual = chefInfantil.preparar(infantil);

        Assertions.assertEquals(respEsperada,respActual);
    }
    @Test
    public void chefVeggie(){

        Menu veggie = new MenuVeggie(100.0, 2,true);
        Chef chefVeggie = new ChefVeggie();

        String respEsperada = "Es un menu vegetariano con 2 salsas y si tiene especias, y sale 105.0";
        String respActual = chefVeggie.preparar(veggie);

        Assertions.assertEquals(respEsperada,respActual);
    }
}
