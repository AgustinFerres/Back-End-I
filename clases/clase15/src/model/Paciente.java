package model;

import java.sql.Date;
import java.time.LocalDate;

public class Paciente {

    private Integer id;
    private String apellido;
    private String nombre;
    private String dni;
    private Date fechaIngreso;
    private Domicilio domicilio;

    public Paciente(Integer id, String apellido, String nombre, String dni, Date fechaIngreso, Domicilio domicilio) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public Paciente(String apellido, String nombre, String dni, Date fechaIngreso, Domicilio domicilio) {
        this.id = 0;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + "\nDNI: " + dni + " Alta: " + fechaIngreso + "\nVive en: " + domicilio.getProvincia() + ", " + domicilio.getLocalidad() + ", " + domicilio.getCalle() + " " + domicilio.getNumero();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
}
