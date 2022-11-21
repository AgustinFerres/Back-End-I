package com.example.proyecto_Integrador.service;

import com.example.proyecto_Integrador.entity.Paciente;
import com.example.proyecto_Integrador.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;


    public Paciente guardar(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscar(Integer id){
        return pacienteRepository.findById(id);
    }

    public void actualizar(Paciente paciente){
        pacienteRepository.save(paciente);
    }

    public void eliminar(Integer id){
        pacienteRepository.deleteById(id);
    }

    public List<Paciente> buscarTodos (){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarXEmail(String email){
        return pacienteRepository.findByEmail(email);
    }

}
