package com.softserveinc;

import java.sql.PreparedStatement;
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
            while (result.next()) {
                Double averageAge = result.getDouble("years");
                System.out.printf("Average age: %.2f", averageAge);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * print time of using library for each reader
     */
    public static void getTimeOfUsingLibrary() {
        try (Statement statement = JDBConnector.getInstance().createStatement()) {
            ResultSet result = statement.executeQuery("SELECT name, surname, DATEDIFF(CURRENT_TIMESTAMP, dateOfCreate) as 'days' FROM Reader;");
            while (result.next()) {
                String name = result.getString("name");
                String surname = result.getString("surname");
                Integer days = result.getInt("days");
                System.out.printf("Name: %s, \t Surname: %s, \t Count of days: %d ", name, surname, days);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * print average number of appeals to the library
     */
    public static void getAverageNumberOfAppeals() {
        String sqlQuery = "SELECT count(idReader) / (count(distinct idReader)) as 'number'" +
                "FROM orderreader WHERE dataOrder Between ? AND ?;";
        try (PreparedStatement statement = JDBConnector.getInstance().prepareStatement(sqlQuery)) {
            statement.setString(1, "2015-07-01");
            statement.setString(2, "2015-12-06");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Double number = result.getDouble("number");
                System.out.printf("Average number of appeals: %.2f ", number);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * print average age of all readers who read some book
     */
    public static void getAverageAgeOfReadersByBook() {
        String sqlQuery = "SELECT (AVG( DATEDIFF(CURRENT_TIMESTAMP, birth) / 365)) as 'years'" +
                " FROM reader join (SELECT idReader FROM orderreader" +
                " join (SELECT inventoryNumber FROM copy WHERE code = (SELECT code FROM book where name= ? LIMIT 1)) as invNumbers" +
                " on orderreader.inventoryNumber = invNumbers.inventoryNumber) as readersId on reader.idReader = readersId.idReader;";
        try (PreparedStatement statement = JDBConnector.getInstance().prepareStatement(sqlQuery)) {
            statement.setString(1, "Бот");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Double years = result.getDouble("years");
                System.out.printf("Average age of readers: %.2f ", years);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
