public class ServicioDescarga
    implements DescargaSpotify{

    @Override
    public String descargar(Usuario usuario) {
        return "Se esta descargando " + usuario.getCancion() + ". \n Por favor espere.";
    }
}
