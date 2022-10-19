public class AnalistaLote extends AnalistaCalidad{

    @Override
    public String comprobarArticulo(Articulo art) {

        if (art.getLote() >= 1000 && art.getLote() <= 2000){
           return getSiguienteAnalista().comprobarArticulo(art);
        }else {
            return "rechazado";
        }
    }
}
