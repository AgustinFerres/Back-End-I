public class ChefClasico extends Chef{

    public ChefClasico() {
    }

    @Override
    protected String armar(Menu menu) {
        return "El menu debe de ser clasico";
    }

    @Override
    protected Double calcularPrecio(Menu menu) {
        return menu.getPrecioBase();
    }
}
