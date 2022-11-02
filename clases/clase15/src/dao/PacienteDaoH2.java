package dao;

import model.Paciente;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class PacienteDaoH2 implements Dao<Paciente>{

    private DomicilioDaoH2 domicilioDaoH2;

    public PacienteDaoH2() {
        this.domicilioDaoH2 = new DomicilioDaoH2();
    }

    private static final String SQL_INSERT="INSERT INTO PACIENTE " +
            "VALUES (?,?,?,?,?, ?)";
    private static final String SQL_SELECT = "SELECT * FROM PACIENTE WHERE ID = ?";
    @Override
    public Paciente guardar(Paciente paciente) {
        System.out.println("Se inició un pedido de incorporación de paciente");
        //va el código que realizabamos con anteriodad
        //ahora la información está en paciente como parametro
        Connection connection=null;
        try{
            //conectarme a la base
            connection=BD.getConnection();
            //insertar
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT);
            psInsert.setInt(1,paciente.getId());
            psInsert.setString(2,paciente.getApellido());
            psInsert.setString(3, paciente.getNombre());
            psInsert.setString(4, paciente.getDni());
            psInsert.setDate(5, paciente.getFechaIngreso());
            psInsert.setInt(6,paciente.getDomicilio().getId());
            psInsert.execute();

            domicilioDaoH2.guardar(paciente.getDomicilio());
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
        return paciente;
    }

    @Override
    public Paciente buscar(Integer id) {
        System.out.println("Se inició un pedido de busqueda de paciente");
        //va el código que realizabamos con anteriodad
        //ahora la información está en paciente como parametro
        Connection connection=null;
        Paciente paciente = null;
        try{
            //conectarme a la base
            connection=BD.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement psSelect= connection.prepareStatement(SQL_SELECT);
            psSelect.setInt(1, id);
            psSelect.execute();

            ResultSet rs = psSelect.getResultSet();
            while (rs.next()){
                paciente = new Paciente(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),  rs.getDate(5), domicilioDaoH2.buscar(rs.getInt(6)));

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
        return paciente;
    }

    @Override
    public void actualizar(Paciente paciente) {

    }

    @Override
    public void eliminar(Paciente paciente) {

    }

    @Override
    public List<Paciente> buscarTodo() {
        return null;
    }
}
