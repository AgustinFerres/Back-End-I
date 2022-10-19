public class CompruebaCalidad {

    private AnalistaCalidad cadena;

    public CompruebaCalidad() {

        cadena = new AnalistaLote();

        cadena.setSiguienteAnalista(new AnalistaPeso());
        cadena.setSiguienteAnalista(new AnalistaEnvase());
    }

    public String checkArticulo (Articulo art){
        return cadena.comprobarArticulo(art);
    }
}
