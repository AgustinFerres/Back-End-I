public class ProxySpotify
    implements DescargaSpotify{

    private DescargaSpotify servicio;

    public ProxySpotify() {
        servicio = new ServicioDescarga();
    }

    @Override
    public String descargar(Usuario usuario) {
        if (usuario.getTipo().equals("Premium")){
            return servicio.descargar(usuario);
        }
        return "Necesitas premium para poder descargar canciones.";
    }
}
