package service;


import dao.*;
import model.Medicamento;

public class MedicamentoService {
    private IDao<Medicamento> medicamentoIDao;
    public MedicamentoService(){

        medicamentoIDao = new MedicamentoIDaoH2();
    }
    public Medicamento guardar(Medicamento medicamento){
        return medicamentoIDao.guardar(medicamento);
    }

    public Medicamento buscar(Integer id){
        return medicamentoIDao.buscar(id);
    }
}
