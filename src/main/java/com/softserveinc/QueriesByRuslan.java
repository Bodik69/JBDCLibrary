package com.softserveinc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ruslan on 09.12.2015.
 */
public class QueriesByRuslan {

    /**
     * How many book in library, that was made in Ukrainians independence.
     */
    public void countBookInIndependence() {
        String query = "SELECT COUNT(*) as count FROM BOOK WHERE YEAR>=1991";
        try {
            Statement statement = JDBConnector.getInstance().createStatement();
            ResultSet set = statement.executeQuery(query);
            while (set.next()) {
                int countOfBook = set.getInt("count");
                System.out.println("Count of book : " + countOfBook);
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
    }

    /**
     * How many times the book was taken? (in general).
     */

    public void findTimesGeneral() {
        String query = "SELECT book.name AS book,COUNT(idOrder) as count"
                + " FROM orderreader INNER JOIN "
                + "(copy INNER JOIN book ON copy.code = book.code)"
                + " ON orderreader.inventoryNumber = copy.inventoryNumber "
                + " GROUP BY NAME";
        try (Statement st = JDBConnector.getInstance().createStatement()) {
            ResultSet set = st.executeQuery(query);
            while (set.next()) {
                String book = set.getString("book");
                int countOfTimesGeneral = set.getInt("count");
                System.out.printf("Book : %s, \t Count : %s", book, countOfTimesGeneral);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * How many times the book was taken? (by copy).
     */
    public void findTimesByCopy() {
        String query = "select inventoryNumber,count(idOrder) as count\n"
                + "from orderreader\n"
                + "group by inventoryNumber";
        try (Statement st = JDBConnector.getInstance().createStatement()) {
            ResultSet set = st.executeQuery(query);
            while (set.next()) {
                String book = set.getString("inventoryNumber");
                int countOfTimesByCopy = set.getInt("count");
                System.out.printf("InventoryNumber : %s, \t Count : %s", book, countOfTimesByCopy);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find AVG of reading.
     */
    public void findAvgOfReading() {
        String query = "select book.name as name,"
                + "avg(TIMESTAMPDIFF(day,orders.dataOrder,orders.dataReturn))as avg_ofReading\n"
                + "from (select * from orderreader "
                + "where orderreader.dataReturn IS NOT NULL) orders inner join \n"
                + "(copy inner join book on copy.code = book.code)\n"
                + "on orders.inventoryNumber = copy.inventoryNumber\n"
                + "group by book.name";
        try (Statement st = JDBConnector.getInstance().createStatement()) {
            ResultSet set = st.executeQuery(query);
            while (set.next()) {
                String book = set.getString("name");
                double avgOfReading = set.getDouble("avg_ofReading");
                System.out.printf("Book : %s, \t AVG : %s", book, avgOfReading);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find the most popular book in the period.
     */
    public void findPopularBookByPeriod() {
        String query = "select book.name\n"
                + "from orderreader inner join \n"
                + "(copy inner join book on copy.code = book.code)\n"
                + "on orderreader.inventoryNumber = copy.inventoryNumber\n"
                + "where orderreader.dataOrder \n"
                + "between ('2015-06-01') and ('2015-09-01')\n"
                + "group by Book.name";
        try (Statement st = JDBConnector.getInstance().createStatement()) {
            ResultSet set = st.executeQuery(query);
            System.out.println("Book");
            while (set.next()) {
                System.out.printf(set.getString("name"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}