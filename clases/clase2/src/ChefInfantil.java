public class ChefInfantil extends Chef{

    public ChefInfantil() {
    }

    @Override
    protected String armar(Menu menu) {
        String respuesta = "";

        if (menu instanceof  MenuInfantil){

            MenuInfantil inf = (MenuInfantil) menu;

            respuesta = "Es un menu infantil con " + inf.getCantidadJuguetes() + " juguetes";
        }

        return respuesta;
    }

    @Override
    protected Double calcularPrecio(Menu menu) {

        Double respuesta = 0.0;

        if (menu instanceof MenuInfantil){

            MenuInfantil inf = (MenuInfantil) menu;

            respuesta = inf.getPrecioBase() + inf.getCantidadJuguetes() * 3;
        }

        return respuesta;
    }
}
