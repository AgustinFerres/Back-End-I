package entidadesNegocio;

import java.util.Date;

public class Turno {

    private Odontologo odontologo;
    private Paciente paciente;
    private Date fecha;
    private Date hora;

    public Turno(Clinica c,Odontologo odontologo, Paciente paciente, Date fecha, Date hora) throws TurnoException {
        if (estaOcupado(c,odontologo,paciente,fecha,hora)){
            throw new TurnoException("Este horario esta ocupado");
        }

        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
    }


    private Boolean estaOcupado(Clinica c, Odontologo o, Paciente p, Date fecha, Date hora){
        boolean respuesta = false;
        try {
            respuesta = c.getTurnos().contains(new Turno(c,o,p,fecha,hora));
        } catch (TurnoException e) {
            e.printStackTrace();
        }
        return respuesta;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
}
