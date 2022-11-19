package com.example.proyecto_Integrador.service;

import com.example.proyecto_Integrador.dao.IDao;
import com.example.proyecto_Integrador.dao.TurnoIDaoList;
import com.example.proyecto_Integrador.model.Turno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    private IDao<Turno> turnoIDao;
    public TurnoService(){

        turnoIDao = new TurnoIDaoList();
    }

    public Turno guardar(Turno turno){
        return turnoIDao.guardar(turno);
    }

    public Turno buscar(Integer id){
        return turnoIDao.buscar(id);
    }

    public void actualizar(Turno turno){
        turnoIDao.actualizar(turno);
    }

    public void eliminar(Integer id){
        turnoIDao.eliminar(id);
    }

    public List<Turno> buscarTodos (){
        return turnoIDao.buscarTodo();
    }
}
