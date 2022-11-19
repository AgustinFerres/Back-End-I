package com.example.proyecto_Integrador.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {

    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(
                "jdbc:h2:~/proyecto","sa"
                ,"sa");
    }
    public static void crearTablas(){
        Connection connection=null;
        try{
            //drop create
            connection = getConnection();
            Statement statement= connection.createStatement();

            statement.execute(
                    "DROP TABLE IF EXISTS ODONTOLOGO;" +
                            "CREATE TABLE ODONTOLOGO (" +
                            "ID INT AUTO_INCREMENT PRIMARY KEY," +
                            "MATRICULA INT ," +
                            "NOMBRE VARCHAR(100) ," +
                            "APELLIDO VARCHAR(100)" +
                            ")"
            );
            statement.execute(
                    "DROP TABLE IF EXISTS DOMICILIO;" +
                            "CREATE TABLE DOMICILIO (" +
                            "ID INT AUTO_INCREMENT PRIMARY KEY," +
                            "CALLE VARCHAR(100) ," +
                            "NUMERO INT ," +
                            "LOCALIDAD VARCHAR(100)," +
                            "PROVINCIA VARCHAR(100)" +
                            ")"
            );
            statement.execute(
                    "DROP TABLE IF EXISTS PACIENTE;" +
                            "CREATE TABLE PACIENTE (" +
                            "ID INT AUTO_INCREMENT PRIMARY KEY," +
                            "NOMBRE VARCHAR(100) ," +
                            "APELLIDO VARCHAR(100) ," +
                            "DNI VARCHAR(100) ," +
                            "FECHA_INGRESO DATE ," +
                            "DOMICILIO_ID INT," +
                            "EMAIL VARCHAR(100)" +
                            ")"
            );
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
    }
}

