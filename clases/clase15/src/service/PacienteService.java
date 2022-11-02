package service;

import dao.Dao;
import dao.PacienteDaoH2;
import model.Paciente;

import java.util.List;

public class PacienteService {
    private Dao<Paciente> pacienteDao;
    public PacienteService(){

        pacienteDao = new PacienteDaoH2();
    }
    public Paciente guardar(Paciente paciente){
        return pacienteDao.guardar(paciente);
    }

    public Paciente buscar(Integer id){
        return pacienteDao.buscar(id);
    }

    public void actualizar(Paciente paciente){
        pacienteDao.actualizar(paciente);
    }

    public void eliminar(Integer id){
        pacienteDao.eliminar(id);
    }

    public List<Paciente> buscarTodos (){
        return pacienteDao.buscarTodo();
    }
}
