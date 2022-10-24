import java.time.LocalDate;
import java.util.Date;

public interface Busqueda {
    void buscar(String origen, LocalDate fechaSalida, LocalDate fechaRegreso, String destino);
}
