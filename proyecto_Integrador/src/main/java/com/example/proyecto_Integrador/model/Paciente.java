package com.example.proyecto_Integrador.model;

import java.time.LocalDate;

public class Paciente {

    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fecha_ingreso;
    private Domicilio domicilio;
    private String email;
    public Paciente() {
    }

    public Paciente(Integer id, String nombre, String apellido, String dni, LocalDate fecha_ingreso, Domicilio domicilio, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fecha_ingreso = fecha_ingreso;
        this.domicilio = domicilio;
        this.email = email;
    }

    public Paciente(String nombre, String apellido, String dni, LocalDate fecha_ingreso, Domicilio domicilio, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fecha_ingreso = fecha_ingreso;
        this.domicilio = domicilio;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
