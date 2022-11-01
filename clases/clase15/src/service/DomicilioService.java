package service;

import dao.Dao;
import dao.DomicilioDaoH2;
import dao.PacienteDaoH2;
import model.Domicilio;
import model.Paciente;

public class DomicilioService {

    private Dao<Domicilio> domicilioDao;
    public DomicilioService(){

        domicilioDao = new DomicilioDaoH2();
    }
    public Domicilio guardar(Domicilio domicilio){
        return domicilioDao.guardar(domicilio);
    }

    public Domicilio buscar(Integer id){
        return domicilioDao.buscar(id);
    }
}
