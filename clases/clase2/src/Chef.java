public abstract class Chef {

    public Chef() {
    }

    public  String preparar(Menu menu){
        String respuesta = "No hay ingredientes";

        respuesta = armar(menu) + ", y sale " +  calcularPrecio(menu);

        return respuesta;
    }

    protected abstract String armar(Menu menu);

    protected abstract Double calcularPrecio(Menu menu);
}
