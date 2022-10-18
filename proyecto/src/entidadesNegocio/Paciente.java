package entidadesNegocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Paciente extends Persona{

    private String domicilio;
    private String dni;
    private Date fechaAlta;
    private List<Turno> turnos;

    public Paciente(String nombre, String apellido, String domicilio, String dni, Date fechaAlta) {
        super(nombre, apellido);
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
        turnos = new ArrayList<>();
    }

    public String hacerReserva(Clinica c, Odontologo o, Date fecha, Date hora){
        try {
            Turno turno = new Turno(c, o, this, fecha, hora);
        } catch (TurnoException e) {
            throw new RuntimeException(e);
        }
        return "Se a completado su reserva";
    }


}
