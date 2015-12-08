import java.sql.*;

/**
 * Created by Ihor Sokolyk on 08.12.2015.
 */
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection con;

    public static void main(String[] args) throws SQLException {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement st = con.createStatement();
        ResultSet resultSet = st.executeQuery("Select * from book");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
        }
    }

}
