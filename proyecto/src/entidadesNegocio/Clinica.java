package entidadesNegocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Clinica {

    private List<Odontologo> odontologos;
    private List<Paciente> pacientes;
    private List<Turno> turnos;

    public Clinica() {
        odontologos = new ArrayList<>();
        pacientes = new ArrayList<>();
        turnos = new ArrayList<>();
    }

    public Turno darTurno(Paciente p, LocalDate date, LocalTime hour, Odontologo o) throws ClinicaException {
        if (!pacientes.contains(p)){
            throw new ClinicaException("Primero tienes que registarte");
        }
        if (!odontologos.contains(o)){
            throw new ClinicaException("Ese odontologo no esta nuestro sistema");
        }
        if (estaOcupado(new Turno(o,p,date, hour))){
            throw new ClinicaException("Ese horario esta ocupado, por favor elija otro");
        }
        turnos.add(new Turno(o,p,date, hour));
        return turnos.get(turnos.size() - 1);
    }
    private Boolean estaOcupado(Turno t){
        for (Turno turno : turnos) {
            if (turno.equals(t)){
                return true;
            }
        }
        return false;
    }
    public List<Odontologo> getOdontologos() {
        return odontologos;
    }
    public void agregarOdontologo(Odontologo o){
        odontologos.add(o);
    }
    public void quitarOdontologo(Odontologo o){
        odontologos.remove(o);
    }
    public void setOdontologos(List<Odontologo> odontologos) {
        this.odontologos = odontologos;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }
    public void agregarPaciente(Paciente p){
        pacientes.add(p);
    }
    public void quitarPaciente(Paciente p){
        pacientes.remove(p);
    }
    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }
    public void agregarTurno(Turno t){
        turnos.add(t);
    }
    public void quitarTurno(Turno p){
        turnos.remove(p);
    }
    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }


}
