package com.example.proyecto_Integrador.repository;

import com.example.proyecto_Integrador.entity.Odontologo;
import com.example.proyecto_Integrador.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

    Optional<Odontologo> findByNombreAndApellido(String nombre, String apellido);

}
