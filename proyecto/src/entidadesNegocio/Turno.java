package entidadesNegocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Turno {

    private Odontologo odontologo;
    private Paciente paciente;
    private LocalDate fecha;
    private LocalTime hora;

    public Turno(Odontologo odontologo, Paciente paciente, LocalDate fecha, LocalTime hora){
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
    }

    @Override
    public boolean equals(Object obj) {
        Turno t = (Turno) obj;
        return  this.odontologo.equals(t.odontologo) && this.fecha.equals(t.fecha) && this.hora.equals(t.hora);
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
