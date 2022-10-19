package chef;
import menu.*;
public class ChefVeggie extends Chef{

    public ChefVeggie() {
    }

    @Override
    protected String armar(Menu menu) {

        String respuesta = "";

        if (menu instanceof MenuVeggie){

            MenuVeggie veg = (MenuVeggie) menu;

            String especias = "no tiene especias";

            if (veg.getEspecias()){
                especias = "si tiene especias";
            }

            respuesta = "Es un menu vegetariano con " + veg.getCantidadSalsas() + " salsas y " + especias;
        }

        return respuesta;
    }

    @Override
    protected Double calcularPrecio(Menu menu) {

        Double respuesta = 0.0;

        if (menu instanceof MenuVeggie) {

            MenuVeggie veg = (MenuVeggie) menu;
            if (veg.getEspecias()){
                respuesta = veg.getPrecioBase() * 1.01 + veg.getCantidadSalsas() * 2;
            }else {
                respuesta = veg.getPrecioBase() + veg.getCantidadSalsas() * 2;
            }
        }

        return respuesta;
    }
}
