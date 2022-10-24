import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BuscarHotel {

    private List<Hotel> hoteles;

    public BuscarHotel() {
        this.hoteles = new ArrayList<>();
    }

    public void agregarHotel(Hotel h){
        hoteles.add(h);
    }
    public void quitarHotel(Hotel h){
        hoteles.remove(h);
    }
    public String buscarHotel(LocalDate fechaSalida, LocalDate fechaRegreso, String ciudad){
        String respuesta = "";
        for (Hotel hotel : hoteles) {
            if (hotel.getFechasDisponibles().contains(fechaSalida) && hotel.getFechasDisponibles().contains(fechaRegreso) && hotel.getCiudad().equals(ciudad)){
                respuesta += hotel + "\n";
            }
        }
        return respuesta.equals("") ? respuesta = "No se encontraron coincidencias" : respuesta;
    }

    public List<Hotel> getHoteles() {
        return hoteles;
    }
}
