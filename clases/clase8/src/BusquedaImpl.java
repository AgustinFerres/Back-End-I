import java.time.LocalDate;
import java.util.Date;

public class BusquedaImpl
    implements Busqueda{

    private BuscarHotel buscadorHoteles;
    private BuscarVuelo buscadorVuelos;

    public BusquedaImpl() {
        buscadorHoteles = new BuscarHotel();
        buscadorVuelos = new BuscarVuelo();
    }

    public void agregarHotel (Hotel h){
        buscadorHoteles.agregarHotel(h);
    }
    public void agregarVuelo (Vuelo v){
        buscadorVuelos.agregarVuelo(v);
    }

    @Override
    public void buscar(String origen, LocalDate fechaSalida, LocalDate fechaRegreso, String destino) {
        System.out.println("Vuelos para el " + fechaSalida.getDayOfMonth() + " desde " + origen + ": \n" + buscadorVuelos.buscarVuelo(fechaSalida,fechaRegreso, origen, destino) + "\nHoteles disponibles desde el " + fechaSalida.getDayOfMonth() + " hasta " + fechaRegreso.getDayOfMonth() + ": \n" + buscadorHoteles.buscarHotel(fechaSalida,fechaRegreso,destino));
    }

    public BuscarHotel getBuscadorHoteles() {
        return buscadorHoteles;
    }

    public BuscarVuelo getBuscadorVuelos() {
        return buscadorVuelos;
    }
}
