package com.example.proyecto_Integrador.service;

import com.example.proyecto_Integrador.entity.Turno;
import com.example.proyecto_Integrador.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    public Turno guardar(Turno turno){
        return turnoRepository.save(turno);
    }

    public Optional<Turno> buscar(Long id){
        return turnoRepository.findById(id);
    }

    public void actualizar(Turno turno){
        turnoRepository.save(turno);
    }

    public void eliminar(Long id){
        turnoRepository.deleteById(id);
    }

    public List<Turno> buscarTodos (){
        return turnoRepository.findAll();
    }
}
