package dao;

import model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class PacienteDaoH2 implements Dao<Paciente>{

    private DomicilioDaoH2 domicilioDaoH2;
    private static final Logger LOGGER= Logger.getLogger(PacienteDaoH2.class);

    public PacienteDaoH2() {
        this.domicilioDaoH2 = new DomicilioDaoH2();
    }

    private static final String SQL_INSERT_WITHOUT_ID = "INSERT INTO PACIENTE " +
            "VALUES (DEFAULT,?,?,?,?, ?)";
    private static final String SQL_INSERT_WITH_ID = "INSERT INTO PACIENTE " +
            "VALUES (?,?,?,?,?, ?)";
    private static final String SQL_SELECT = "SELECT * FROM PACIENTE WHERE ID = ?";
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
                psInsert.setDate(4, paciente.getFechaIngreso());
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
                psInsert.setDate(5, paciente.getFechaIngreso());
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

    }

    @Override
    public void eliminar(Paciente paciente) {

    }

    @Override
    public List<Paciente> buscarTodo() {
        return null;
    }
}
