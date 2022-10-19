public class AnalistaEnvase extends AnalistaCalidad{

    @Override
    public String comprobarArticulo(Articulo art) {

        if (art.getEnvasado().equals("sano")  || art.getEnvasado().equals("casi sano")){
            return "aceptado";
        }else {
            return "rechazado";
        }
    }
}
