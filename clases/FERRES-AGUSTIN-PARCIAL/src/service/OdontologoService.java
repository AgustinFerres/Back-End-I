package service;

import dao.IDao;
import dao.OdontologoIDaoH2;
import models.Odontologo;

import java.util.List;

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
