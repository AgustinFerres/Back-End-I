public class AnalistaPeso extends AnalistaCalidad{

    @Override
    public String comprobarArticulo(Articulo art) {

        if (art.getPeso() >= 1200 && art.getPeso() <= 1300){
            return getSiguienteAnalista().comprobarArticulo(art);
        }else {
            return "rechazado";
        }
    }
}
