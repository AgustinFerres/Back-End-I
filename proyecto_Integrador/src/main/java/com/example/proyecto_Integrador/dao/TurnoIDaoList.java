package com.example.proyecto_Integrador.dao;

import com.example.proyecto_Integrador.model.Turno;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class TurnoIDaoList implements IDao<Turno> {


    private List<Turno> turnos = new ArrayList<>();

    @Override
    public Turno guardar(Turno turno) {
        turno.setId(turnos.size() + 1);
        turno.getPaciente().setId(1);
        turno.getPaciente().getDomicilio().setId(1);
        turno.getOdontologo().setId(1);
        turnos.add(turno);
        return turno;
    }

    @Override
    public Turno buscar(Integer id) {
        for (Turno turno : turnos) {
            if (turno.getId().equals(id) ){
                return turno;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Turno turno) {
        eliminar(turno.getId());
        guardar(turno);
    }

    @Override
    public void eliminar(Integer id) {
        Turno turnoAEliminar = buscar(id);
        if (turnoAEliminar != null){
            turnos.remove(turnoAEliminar);
        }
    }

    @Override
    public List<Turno> buscarTodo() {
        return turnos;
    }

    @Override
    public Turno buscarXString(String string) {
        return null;
    }
}
