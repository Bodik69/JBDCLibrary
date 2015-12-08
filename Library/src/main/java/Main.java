import com.softserveinc.dao.JdbcConnection;

import java.sql.*;

/**
 * Created by Ihor Sokolyk on 08.12.2015.
 */
public class Main {

    public static void main(String[] args) throws SQLException {


        Statement st = JdbcConnection.getInstance().createStatement();
        ResultSet resultSet = st.executeQuery("Select * from book");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
        }
    }

}
