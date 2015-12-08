package com.softserveinc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Богдан on 08.12.2015.
 */
public class BooksDAO {

    /**
     * print count of copies of some book
     */
    public static void getCountOfCopies() {
        String sqlQuery = "SELECT count(*) as 'count' FROM copy WHERE code = (SELECT code FROM book WHERE name= ? LIMIT 1);";
        try (PreparedStatement statement = JDBConnector.getInstance().prepareStatement(sqlQuery)) {
            statement.setString(1, "Бот");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Integer count= result.getInt("count");
                System.out.printf("Count of copies: %d ", count);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
