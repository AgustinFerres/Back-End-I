package dao;

import models.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoIDaoH2 implements IDao<Odontologo> {

    private static final Logger LOGGER= Logger.getLogger(OdontologoIDaoH2.class);
    private static final String SQL_INSERT_WITHOUT_ID = "INSERT INTO ODONTOLOGO VALUES (DEFAULT,?,?,?)";
    private static final String SQL_INSERT_WITH_ID = "INSERT INTO ODONTOLOGO VALUES (?,?,?,?)";
    private static final String SQL_SELECT = "SELECT * FROM ODONTOLOGO WHERE ID = ?";
    private static final String SQL_UDPATE = "UPDATE ODONTOLOGO SET MATRICULA = ?, NOMBRE = ?, APELLIDO = ? WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM ODONTOLOGO WHERE ID = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM ODONTOLOGO";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Se inicio un pedido de incorporacion de odontologo");
        Connection connection = null;
        try{
            connection=BD.getConnection();
            if (odontologo.getId() == null){
                PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT_WITHOUT_ID, Statement.RETURN_GENERATED_KEYS);
                psInsert.setInt(1, odontologo.getMatricula());
                psInsert.setString(2, odontologo.getNombre());
                psInsert.setString(3, odontologo.getApellido());
                psInsert.execute();

                ResultSet rs = psInsert.getGeneratedKeys();
                while (rs.next()){
                    odontologo.setId(rs.getInt(1));
                }

            }else {
                PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT_WITH_ID);
                psInsert.setInt(1, odontologo.getId());
                psInsert.setInt(2,odontologo.getMatricula());
                psInsert.setString(3, odontologo.getNombre());
                psInsert.setString(4, odontologo.getApellido());
                psInsert.execute();
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
        return odontologo;
    }

    @Override
    public Odontologo buscar(Integer id) {
        LOGGER.info("Se inicio un pedido de busqueda de odontologo");
        Connection connection = null;
        Odontologo odontologo = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelect= connection.prepareStatement(SQL_SELECT);
            psSelect.setInt(1, id);
            psSelect.execute();

            ResultSet rs = psSelect.getResultSet();
            while (rs.next()){
                odontologo = new Odontologo(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));

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
        return odontologo;
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        LOGGER.info("Se inicio un pedido de update de odontologo");
        Connection connection = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelect= connection.prepareStatement(SQL_UDPATE);
            psSelect.setInt(1, odontologo.getMatricula());
            psSelect.setString(2, odontologo.getNombre());
            psSelect.setString(3, odontologo.getApellido());
            psSelect.setInt(4, odontologo.getId());
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
        LOGGER.info("Se inicio un pedido de delete de odontologo");
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SQL_DELETE);
            psSelect.setInt(1, id);
            psSelect.execute();
            LOGGER.warn("Se elimino el odontologo de id " + id);
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
    public List<Odontologo> buscarTodo() {

        List<Odontologo> odontologos = new ArrayList<>();
        Connection connection=null;

        LOGGER.info("Iniciando la busqueda de todos los odontologos");
        try{
            connection=BD.getConnection();
            Odontologo odontologo = null;

            PreparedStatement psSelectAll=connection.prepareStatement(SQL_SELECT_ALL);

            ResultSet rs=psSelectAll.executeQuery();
            while(rs.next()){
                odontologo = new Odontologo(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4));

                odontologos.add(odontologo);
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
        return odontologos;
    }
}
