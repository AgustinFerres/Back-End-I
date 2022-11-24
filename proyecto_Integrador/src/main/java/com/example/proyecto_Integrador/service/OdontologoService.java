package com.example.proyecto_Integrador.service;

import com.example.proyecto_Integrador.entity.Odontologo;
import com.example.proyecto_Integrador.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;

    public Odontologo guardar(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscar(Long id){
        return odontologoRepository.findById(id);
    }

    public void actualizar(Odontologo odontologo){
        odontologoRepository.save(odontologo);
    }

    public void eliminar(Long id){
        odontologoRepository.deleteById(id);
    }

    public List<Odontologo> buscarTodos (){
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> buscarXNombreYApellido(String nombre,String apellido){
        return odontologoRepository.findByNombreAndApellido(nombre,apellido);
    }
}