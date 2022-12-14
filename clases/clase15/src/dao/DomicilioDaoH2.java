package dao;

import model.Domicilio;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDaoH2 implements Dao<Domicilio>{

    private static final Logger LOGGER= Logger.getLogger(DomicilioDaoH2.class);
    private static final String SQL_INSERT_WITHOUT_ID = "INSERT INTO DOMICILIO VALUES (DEFAULT,?,?,?,?)";
    private static final String SQL_INSERT_WITH_ID = "INSERT INTO DOMICILIO VALUES (?,?,?,?,?)";
    private static final String SQL_SELECT = "SELECT * FROM DOMICILIO WHERE ID = ?";
    private static final String SQL_UDPATE = "UPDATE DOMICILIO SET CALLE = ?, NUMERO = ?, LOCALIDAD = ?, PROVINCIA = ? WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM DOMICILIO WHERE ID = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM DOMICILIO";
    @Override
    public Domicilio guardar(Domicilio domicilio) {
        LOGGER.info("Se inició un pedido de incorporación de domicilio");
        //va el código que realizabamos con anteriodad
        //ahora la información está en domicilio como parametro
        Connection connection=null;
        try{
            //conectarme a la base
            connection=BD.getConnection();
            //insertar
            if (domicilio.getId() == null) {
                PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT_WITHOUT_ID, Statement.RETURN_GENERATED_KEYS);
                psInsert.setString(1, domicilio.getCalle());
                psInsert.setInt(2, domicilio.getNumero());
                psInsert.setString(3, domicilio.getLocalidad());
                psInsert.setObject(4, domicilio.getProvincia());
                psInsert.execute();

                ResultSet rs = psInsert.getGeneratedKeys();
                while (rs.next()){
                    domicilio.setId(rs.getInt(1));
                }
            }else {
                PreparedStatement psInsert = connection.prepareStatement(
                        SQL_INSERT_WITH_ID);
                psInsert.setInt(1,domicilio.getId());
                psInsert.setString(2, domicilio.getCalle());
                psInsert.setInt(3, domicilio.getNumero());
                psInsert.setString(4, domicilio.getLocalidad());
                psInsert.setObject(5, domicilio.getProvincia());
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
        return domicilio;
    }

    @Override
    public Domicilio buscar(Integer id) {
        LOGGER.info("Se inició un pedido de busqueda de domicilio");
        //va el código que realizabamos con anteriodad
        //ahora la información está en domicilio como parametro
        Connection connection=null;
        Domicilio domicilio = null;
        try{
            //conectarme a la base
            connection=BD.getConnection();
            PreparedStatement psSelect= connection.prepareStatement(SQL_SELECT);
            psSelect.setInt(1, id);
            psSelect.execute();

            ResultSet rs = psSelect.getResultSet();
            while (rs.next()){
                domicilio = new Domicilio(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getString(4), rs.getString(5) );

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
        return domicilio;
    }

    @Override
    public void actualizar(Domicilio domicilio) {
        LOGGER.info("Se inició un pedido de update de domicilio");
        //va el código que realizabamos con anteriodad
        //ahora la información está en domicilio como parametro
        Connection connection=null;
        try{
            //conectarme a la base

            connection=BD.getConnection();
            PreparedStatement psSelect= connection.prepareStatement(SQL_UDPATE);
            psSelect.setString(1, domicilio.getCalle());
            psSelect.setInt(2, domicilio.getNumero());
            psSelect.setString(3, domicilio.getLocalidad());
            psSelect.setString(4, domicilio.getProvincia());
            psSelect.setInt(5, domicilio.getId());
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
        LOGGER.info("Se inició un pedido de delete de domicilio");
        //va el código que realizabamos con anteriodad
        //ahora la información está en domicilio como parametro
        Connection connection=null;
        try{
            //conectarme a la base

            connection=BD.getConnection();
            PreparedStatement psSelect= connection.prepareStatement(SQL_DELETE);
            psSelect.setInt(1, id);
            psSelect.execute();
            LOGGER.warn("Se elimino el domicilio de id " + id);
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
    public List<Domicilio> buscarTodo() {
        List<Domicilio> domicilios = new ArrayList<>();
        Connection connection=null;
        LOGGER.info("Iniciando la busqueda de todos los domicilios");
        try{
            connection=BD.getConnection();
            Domicilio domicilio = null;

            PreparedStatement psSelectAll=connection.prepareStatement(SQL_SELECT_ALL);

            ResultSet rs=psSelectAll.executeQuery();
            while(rs.next()){
                //completar el domicilio
                domicilio = new Domicilio(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
                //incorporar el domicilio
                domicilios.add(domicilio);
            }
        }
        catch (Exception e){
            LOGGER.error("Error: "+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return domicilios;
    }
}
