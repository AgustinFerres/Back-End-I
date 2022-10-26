package entidadesNegocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Paciente extends Persona{

    private String domicilio;
    private String dni;
    private LocalDate fechaAlta;
    private List<Turno> turnos;

    public Paciente(String nombre, String apellido, String domicilio, String dni, LocalDate fechaAlta) {
        super(nombre, apellido);
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
        turnos = new ArrayList<>();
    }

    public void hacerReserva(Clinica c, Odontologo o, LocalDate fecha, LocalTime hora){
        try {
            turnos.add(c.darTurno(this, fecha, hora , o));
        } catch (ClinicaException e) {
            e.printStackTrace();
        }
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }
}
