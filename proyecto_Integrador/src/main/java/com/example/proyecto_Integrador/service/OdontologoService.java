package com.example.proyecto_Integrador.service;

import com.example.proyecto_Integrador.dao.IDao;
import com.example.proyecto_Integrador.dao.OdontologoIDaoH2;
import com.example.proyecto_Integrador.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;
    public OdontologoService(){

        odontologoIDao = new OdontologoIDaoH2();
    }

    public Odontologo guardar(Odontologo odontologo){
        return odontologoIDao.guardar(odontologo);
    }

    public Odontologo buscar(Integer id){
        return odontologoIDao.buscar(id);
    }

    public void actualizar(Odontologo odontologo){
        odontologoIDao.actualizar(odontologo);
    }

    public void eliminar(Integer id){
        odontologoIDao.eliminar(id);
    }

    public List<Odontologo> buscarTodos (){
        return odontologoIDao.buscarTodo();
    }
}