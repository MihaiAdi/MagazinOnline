import java.sql.*;

public class conectare {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=MagazinOnline;encrypt=true;trustServerCertificate=true";
        String user = "adi";
        String pass = "12345";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            //testare conexiune
            String sql = "SELECT * FROM Clienti";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getString(2) + " " + result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}