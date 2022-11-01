package service;

import dao.Dao;
import dao.PacienteDaoH2;
import model.Paciente;

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
}
