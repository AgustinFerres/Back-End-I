package com.example.proyecto_Integrador.service;

import com.example.proyecto_Integrador.dao.IDao;
import com.example.proyecto_Integrador.dao.PacienteIDaoH2;
import com.example.proyecto_Integrador.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private IDao<Paciente> pacienteIDao;
    public PacienteService(){

        pacienteIDao = new PacienteIDaoH2();
    }

    public Paciente guardar(Paciente paciente){
        return pacienteIDao.guardar(paciente);
    }

    public Paciente buscar(Integer id){
        return pacienteIDao.buscar(id);
    }

    public void actualizar(Paciente paciente){
        pacienteIDao.actualizar(paciente);
    }

    public void eliminar(Integer id){
        pacienteIDao.eliminar(id);
    }

    public List<Paciente> buscarTodos (){
        return pacienteIDao.buscarTodo();
    }

    public Paciente buscarXEmail(String email){
        return pacienteIDao.buscarXString(email);
    }

}
