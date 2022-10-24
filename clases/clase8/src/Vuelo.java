import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vuelo {

    private String id;
    private String areolinea;
    private String origen;
    private String destino;
    private List<LocalDate> fechasDisponibles;

    public Vuelo(String id, String areolinea, String origen, String destino) {
        this.id = id;
        this.areolinea = areolinea;
        this.origen = origen;
        this.destino = destino;
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
        return "id: " + id + "\nareolinea: " + areolinea;
    }

    public String getId() {
        return id;
    }

    public String getAreolinea() {
        return areolinea;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public List<LocalDate> getFechasDisponibles() {
        return fechasDisponibles;
    }
}
