import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BuscarVuelo {

    private List<Vuelo> vuelos;

    public BuscarVuelo() {
        this.vuelos = new ArrayList<>();
    }

    public void agregarVuelo(Vuelo v){
        vuelos.add(v);
    }
    public void quitarVuelo(Vuelo v){
        vuelos.remove(v);
    }
    public String buscarVuelo(LocalDate fechaSalida, LocalDate fechaRegreso, String origen, String destino){
        String respuesta = "";
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getFechasDisponibles().contains(fechaSalida) && vuelo.getFechasDisponibles().contains(fechaRegreso) && vuelo.getOrigen().equals(origen) && vuelo.getDestino().equals(destino)){
                respuesta += vuelo + "\n";
            }
        }
        return respuesta.equals("") ? respuesta = "No se encontraron coincidencias" : respuesta;
    }
}
