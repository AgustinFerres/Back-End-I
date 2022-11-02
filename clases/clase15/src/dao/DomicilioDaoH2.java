package dao;

import model.Domicilio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DomicilioDaoH2 implements Dao<Domicilio>{

    public static final String SQL_INSERT="INSERT INTO DOMICILIO " +
            "VALUES (?,?,?,?,?)";
    public static final String SQL_SELECT = "SELECT * FROM DOMICILIO WHERE ID = ?";
    @Override
    public Domicilio guardar(Domicilio domicilio) {
        System.out.println("Se inició un pedido de incorporación de domicilio");
        //va el código que realizabamos con anteriodad
        //ahora la información está en domicilio como parametro
        Connection connection=null;
        try{
            //conectarme a la base
            connection=BD.getConnection();
            //insertar
            PreparedStatement psInsert= connection.prepareStatement(
                    SQL_INSERT);
            psInsert.setInt(1,domicilio.getId());
            psInsert.setString(2,domicilio.getCalle());
            psInsert.setInt(3, domicilio.getNumero());
            psInsert.setString(4, domicilio.getLocalidad());
            psInsert.setObject(5, domicilio.getProvincia());
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
        return domicilio;
    }

    @Override
    public Domicilio buscar(Integer id) {
        System.out.println("Se inició un pedido de busqueda de domicilio");
        //va el código que realizabamos con anteriodad
        //ahora la información está en domicilio como parametro
        Connection connection=null;
        Domicilio domicilio = null;
        try{
            //conectarme a la base
            connection=BD.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement psSelect= connection.prepareStatement(SQL_SELECT);
            psSelect.setInt(1, id);
            psSelect.execute();

            ResultSet rs = psSelect.getResultSet();
            while (rs.next()){
                domicilio = new Domicilio(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(4), rs.getString(5) );

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
        return domicilio;
    }

    @Override
    public void actualizar(Domicilio domicilio) {

    }

    @Override
    public void eliminar(Domicilio domicilio) {

    }

    @Override
    public List<Domicilio> buscarTodo() {
        return null;
    }
}
