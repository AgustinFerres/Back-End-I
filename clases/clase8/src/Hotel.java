import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String nombre;
    private String ciudad;
    private List<LocalDate> fechasDisponibles;

    public Hotel(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.fechasDisponibles = new ArrayList<>();
    }

    public void agregarFecha (LocalDate fecha){
        fechasDisponibles.add(fecha);
    }

    public void quitarFecha (LocalDate fecha){
        fechasDisponibles.remove(fecha);
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public List<LocalDate> getFechasDisponibles() {
        return fechasDisponibles;
    }
}
