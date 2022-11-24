package com.example.proyecto_Integrador.repository;

import com.example.proyecto_Integrador.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {


    Optional<Paciente> findByEmail(String email);
}
