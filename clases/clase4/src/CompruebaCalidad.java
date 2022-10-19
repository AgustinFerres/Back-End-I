public class CompruebaCalidad {

    private AnalistaCalidad cadena;

    public CompruebaCalidad() {

        cadena = new AnalistaLote();

        AnalistaCalidad analistaPeso = new AnalistaPeso();

        cadena.setSiguienteAnalista(analistaPeso);
        analistaPeso.setSiguienteAnalista(new AnalistaEnvase());
    }

    public String checkArticulo (Articulo art){
        return cadena.comprobarArticulo(art);
    }
}
