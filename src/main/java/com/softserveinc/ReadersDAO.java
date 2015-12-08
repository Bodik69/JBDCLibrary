package com.softserveinc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ReadersDAO {

    /**
     * print readers who did not return books on time
     */
    public static void getListOfDebtors() {
        try (Statement statement = JDBConnector.getInstance().createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM reader where idReader IN" +
                    " (SELECT idReader FROM orderreader WHERE" +
                    " (dataReturn IS NULL) && DATEDIFF(CURRENT_TIMESTAMP, dataOrder) > 31);");
            while(result.next()) {
                String name = result.getString("name");
                String surname = result.getString("surname");
                String address = result.getString("address");
                String phone = result.getString("phone");
                String birth = result.getString("birth");
                String dateOfCreate = result.getString("dateOfCreate");
                System.out.printf("Name: %s, \t Surname: %s, \t Address: %s, \t Phone: %s, \t Birth: %s", name, surname, address, phone, birth);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * print average age of all readers
     */
    public static void getAverageAgeOfReaders() {
        try (Statement statement = JDBConnector.getInstance().createStatement()) {
            ResultSet result = statement.executeQuery("SELECT (AVG( DATEDIFF(CURRENT_TIMESTAMP, birth) / 365)) as 'years' FROM Reader;");
            while(result.next()) {
                Double averageAge = result.getDouble("years");
                System.out.printf("Average age: %.2f", averageAge);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
