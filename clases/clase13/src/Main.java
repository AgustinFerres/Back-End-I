import java.sql.*;

public class Main {


    public static void main(String[] args) {

        Connection con = null;
        try {
            con = getConnection();

            Statement statement = con.createStatement();
            statement.execute(
                    "DROP TABLE IF EXISTS ODONTOLOGO;" +
                    "CREATE TABLE ODONTOLOGO (" +
                    "id INT PRIMARY KEY," +
                    "apellido VARCHAR(50)," +
                    "nombre VARCHAR(50)," +
                    "matricula VARCHAR(50)" +
                    ");"
            );

            PreparedStatement preparedStatementInsert = con.prepareStatement(
                    "INSERT INTO ODONTOLOGO VALUES (?,?,?,?);"
            );
            PreparedStatement preparedStatementUpdate = con.prepareStatement(
                    "UPDATE ODONTOLOGO SET  matricula = ? WHERE id = ?;"
            );


            preparedStatementInsert.setInt(1,1);
            preparedStatementInsert.setString(2,"Velez");
            preparedStatementInsert.setString(3,"Pablo");
            preparedStatementInsert.setString(4,"CARTAGENA");
            preparedStatementInsert.execute();

            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM ODONTOLOGO");
            while (rs.next()){
                System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }

            preparedStatementUpdate.setString(1,"MEDELLIN");
            preparedStatementUpdate.setInt(2,1);
            preparedStatementUpdate.execute();

            ResultSet rs2 = statement.executeQuery(
                    "SELECT * FROM ODONTOLOGO");
            while (rs2.next()){
                System.out.println(rs2.getString(2) + " " + rs2.getString(3) + " " + rs2.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static Connection getConnection () throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/clase13", "sa", "sa");
    }
}
