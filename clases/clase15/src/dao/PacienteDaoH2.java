package dao;

import model.Domicilio;
import model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteDaoH2 implements Dao<Paciente>{

    private DomicilioDaoH2 domicilioDaoH2;
    private static final Logger LOGGER= Logger.getLogger(PacienteDaoH2.class);

    public PacienteDaoH2() {
        this.domicilioDaoH2 = new DomicilioDaoH2();
    }

    private static final String SQL_INSERT_WITHOUT_ID = "INSERT INTO PACIENTE VALUES (DEFAULT,?,?,?,?, ?)";
    private static final String SQL_INSERT_WITH_ID = "INSERT INTO PACIENTE VALUES (?,?,?,?,?,?)";
    private static final String SQL_SELECT = "SELECT * FROM PACIENTE WHERE ID = ?";
    private static final String SQL_UDPATE = "UPDATE PACIENTE SET APELLIDO = ?, NOMBRE = ?, DNI = ?, FECHAINGRESO = ?,  ID_DOMICILIO = ? WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM PACIENTE WHERE ID = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM PACIENTE";

    @Override
    public Paciente guardar(Paciente paciente) {
        LOGGER.info("Se inició un pedido de incorporación de paciente");
        //va el código que realizabamos con anteriodad
        //ahora la información está en paciente como parametro
        Connection connection = null;
        try{
            //conectarme a la base
            connection=BD.getConnection();
            //insertar
            if (paciente.getId() == null){
                PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT_WITHOUT_ID, Statement.RETURN_GENERATED_KEYS);
                psInsert.setString(1, paciente.getApellido());
                psInsert.setString(2, paciente.getNombre());
                psInsert.setString(3, paciente.getDni());
                psInsert.setObject(4, paciente.getFechaIngreso());
                domicilioDaoH2.guardar(paciente.getDomicilio());
                psInsert.setInt(5, paciente.getDomicilio().getId());
                psInsert.execute();

                ResultSet rs = psInsert.getGeneratedKeys();
                while (rs.next()){
                    paciente.setId(rs.getInt(1));
                }

            }else {
                PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT_WITH_ID);
                psInsert.setInt(1, paciente.getId());
                psInsert.setString(2,paciente.getApellido());
                psInsert.setString(3, paciente.getNombre());
                psInsert.setString(4, paciente.getDni());
                psInsert.setObject(5, paciente.getFechaIngreso());
                psInsert.setInt(6, paciente.getDomicilio().getId());
                psInsert.execute();

                domicilioDaoH2.guardar(paciente.getDomicilio());
            }

        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                LOGGER.error(ex.getMessage());
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public Paciente buscar(Integer id) {
        LOGGER.info("Se inició un pedido de busqueda de paciente");
        //va el código que realizabamos con anteriodad
        //ahora la información está en paciente como parametro
        Connection connection = null;
        Paciente paciente = null;
        try{
            //conectarme a la base
            connection=BD.getConnection();
            PreparedStatement psSelect= connection.prepareStatement(SQL_SELECT);
            psSelect.setInt(1, id);
            psSelect.execute();

            ResultSet rs = psSelect.getResultSet();
            while (rs.next()){
                paciente = new Paciente(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),  rs.getDate(5).toLocalDate(), domicilioDaoH2.buscar(rs.getInt(6)));

            }

        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                LOGGER.error(ex.getMessage());
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public void actualizar(Paciente paciente) {
        LOGGER.info("Se inició un pedido de update de paciente");
        //va el código que realizabamos con anteriodad
        //ahora la información está en paciente como parametro
        Connection connection = null;
        try{
            //conectarme a la base

            connection=BD.getConnection();
            PreparedStatement psSelect= connection.prepareStatement(SQL_UDPATE);
            psSelect.setString(1, paciente.getApellido());
            psSelect.setString(2, paciente.getNombre());
            psSelect.setString(3, paciente.getDni());
            psSelect.setObject(4, paciente.getFechaIngreso());
            domicilioDaoH2.actualizar(paciente.getDomicilio());
            psSelect.setInt(5, paciente.getDomicilio().getId());
            psSelect.setInt(6, paciente.getId());
            psSelect.execute();

        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                LOGGER.error(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        LOGGER.info("Se inició un pedido de delete de paciente");
        //va el código que realizabamos con anteriodad
        //ahora la información está en paciente como parametro
        Connection connection=null;
        try{
            //conectarme a la base

            connection=BD.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SQL_DELETE);
            psSelect.setInt(1, id);
            psSelect.execute();
            LOGGER.warn("Se elimino el paciente de id " + id);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                LOGGER.error(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Paciente> buscarTodo() {
        List<Paciente> pacientes = new ArrayList<>();
        Connection connection=null;
        LOGGER.info("Iniciando la busqueda de todos los pacientes");
        try{
            connection=BD.getConnection();
            Paciente paciente = null;

            PreparedStatement psSelectAll=connection.prepareStatement(SQL_SELECT_ALL);

            ResultSet rs=psSelectAll.executeQuery();
            while(rs.next()){
                //completar el paciente
                paciente = new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilioDaoH2.buscar(rs.getInt(6)));
                //incorporar el paciente
                pacientes.add(paciente);
            }
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                LOGGER.error(ex.getMessage());
                ex.printStackTrace();
            }
        }
        return pacientes;
    }
}
