package com.example.proyecto_Integrador.dao;

import com.example.proyecto_Integrador.model.Paciente;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PacienteIDaoH2 implements IDao<Paciente>{

    private static final Logger LOGGER = Logger.getLogger(PacienteIDaoH2.class);
    private static final String SQL_INSERT_WITHOUT_ID = "INSERT INTO PACIENTE VALUES (DEFAULT,?,?,?,?,?,?)";
    private static final String SQL_INSERT_WITH_ID = "INSERT INTO PACIENTE VALUES (?,?,?,?,?,?)";
    private static final String SQL_SELECT = "SELECT * FROM PACIENTE WHERE ID = ?";
    private static final String SQL_SELECT_EMAIL = "SELECT * FROM PACIENTE WHERE EMAIL = ?";
    private static final String SQL_UDPATE = "UPDATE PACIENTE SET NOMBRE = ?, APELLIDO = ?, DNI = ?, FECHA_INGRESO = ?, DOMICILIO_ID = ?, EMAIL = ? WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM PACIENTE WHERE ID = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM PACIENTE";

    private static final DomicilioIDaoH2 domiclioIDaoH2 = new DomicilioIDaoH2();

    @Override
    public Paciente guardar(Paciente paciente) {
        LOGGER.info("Se inicio un pedido de incorporacion de paciente");
        Connection connection = null;
        try{
            connection = BD.getConnection();
            if (paciente.getId() == null){
                PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT_WITHOUT_ID, Statement.RETURN_GENERATED_KEYS);
                psInsert.setString(1, paciente.getNombre());
                psInsert.setString(2,paciente.getApellido());
                psInsert.setString(3, paciente.getDni());
                psInsert.setObject(4, paciente.getFecha_ingreso());
                domiclioIDaoH2.guardar(paciente.getDomicilio());
                psInsert.setInt(5, paciente.getDomicilio().getId());
                psInsert.setString(6, paciente.getEmail());
                psInsert.execute();

                ResultSet rs = psInsert.getGeneratedKeys();
                while (rs.next()){
                    paciente.setId(rs.getInt(1));
                }

            }else {
                PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT_WITH_ID);
                psInsert.setInt(1, paciente.getId());
                psInsert.setString(2, paciente.getNombre());
                psInsert.setString(3,paciente.getApellido());
                psInsert.setString(4, paciente.getDni());
                psInsert.setObject(5, paciente.getFecha_ingreso());
                psInsert.setInt(6, paciente.getDomicilio().getId());
                psInsert.setString(7, paciente.getEmail());
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
        return paciente;
    }

    @Override
    public Paciente buscar(Integer id) {
        LOGGER.info("Se inicio un pedido de busqueda de paciente");
        Connection connection = null;
        Paciente paciente = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelect= connection.prepareStatement(SQL_SELECT);
            psSelect.setInt(1, id);
            psSelect.execute();

            ResultSet rs = psSelect.getResultSet();
            while (rs.next()){
                paciente = new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate() , domiclioIDaoH2.buscar(rs.getInt(6)), rs.getString(7));

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
        LOGGER.info("Se inicio un pedido de update de paciente");
        Connection connection = null;
        try{
            connection = BD.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SQL_UDPATE);
            psSelect.setString(1, paciente.getNombre());
            psSelect.setString(2,paciente.getApellido());
            psSelect.setString(3, paciente.getDni());
            psSelect.setObject(4, paciente.getFecha_ingreso());
            psSelect.setInt(5, paciente.getDomicilio().getId());
            psSelect.setString(6, paciente.getEmail());
            psSelect.setInt(5, paciente.getId());
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
        LOGGER.info("Se inicio un pedido de delete de paciente");
        Connection connection = null;
        try{
            connection = BD.getConnection();
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
        Connection connection = null;

        LOGGER.info("Iniciando la busqueda de todos los pacientes");
        try{
            connection = BD.getConnection();
            Paciente paciente = null;

            PreparedStatement psSelectAll=connection.prepareStatement(SQL_SELECT_ALL);

            ResultSet rs=psSelectAll.executeQuery();
            while(rs.next()){
                paciente = new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate() , domiclioIDaoH2.buscar(rs.getInt(6)), rs.getString(7));

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

    @Override
    public Paciente buscarXString(String string) {
        LOGGER.info("Se inicio un pedido de busqueda de paciente");
        Connection connection = null;
        Paciente paciente = null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelect= connection.prepareStatement(SQL_SELECT_EMAIL);
            psSelect.setString(1, string);
            psSelect.execute();

            ResultSet rs = psSelect.getResultSet();
            while (rs.next()){
                paciente = new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate() , domiclioIDaoH2.buscar(rs.getInt(6)), rs.getString(7));

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
}
