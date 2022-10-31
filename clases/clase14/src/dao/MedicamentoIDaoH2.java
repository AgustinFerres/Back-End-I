package dao;

import model.Medicamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class MedicamentoIDaoH2 implements IDao<Medicamento>{
    private static final String SQL_INSERT="INSERT INTO MEDICAMENTOS " +
            "VALUES (?,?,?,?,?,?)";
    private static final String SQL_SELECT = "SELECT * FROM MEDICAMENTOS WHERE ID = ?";
    @Override
    public Medicamento guardar(Medicamento medicamento) {
        System.out.println("Se inició un pedido de incorporación de medicamento");
        //va el código que realizabamos con anteriodad
        //ahora la información está en medicamento como parametro
        Connection connection=null;
        try{
            //conectarme a la base
            connection=BD.getConnection();
            //insertar
            PreparedStatement psInsert= connection.prepareStatement(
                    SQL_INSERT);
            psInsert.setInt(1,medicamento.getId());
            psInsert.setString(2,medicamento.getNombre());
            psInsert.setString(3, medicamento.getLaboratorio());
            psInsert.setInt(4,medicamento.getCantidad());
            psInsert.setDouble(5,medicamento.getPrecio());
            psInsert.setInt(6,medicamento.getCodigo());
            psInsert.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return medicamento;
    }

    @Override
    public Medicamento buscar(Integer id) {
        System.out.println("Se inició un pedido de busqueda de medicamento");
        //va el código que realizabamos con anteriodad
        //ahora la información está en medicamento como parametro
        Connection connection=null;
        Medicamento medicamento = null;
        try{
            //conectarme a la base
            connection=BD.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement psSelect= connection.prepareStatement(SQL_SELECT);
            psSelect.setInt(1, id);
            psSelect.execute();

            ResultSet rs = psSelect.getResultSet();
            while (rs.next()){
                medicamento = new Medicamento(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4), rs.getDouble(5), rs.getInt(6) );

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return medicamento;
    }
}
