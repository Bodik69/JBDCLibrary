package com.softserveinc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Ihor Sokolyk on 08.12.2015.
 */
public class QueriesByIhor {
    /**
     * Method finds if book 'Зона покриття' is available.
     */
    public void ifBookIsAvailable() {
        System.out.println("Title\t\t\tName\tSurname\tAvailableCount");
        try {
            Statement st = JDBConnector.getInstance().createStatement();
            ResultSet set = st.executeQuery("SELECT book.name, author.name, author.surname,"
                    + " COUNT(copy.code) AS availableCount"
                    + " FROM book join copy join author"
                    + " ON book.code=copy.code"
                    + " AND book.idAuthor=author.idAuthor"
                    + " WHERE book.name LIKE 'Зона Покриття'"
                    + " AND copy.isInStock=1"
                    + " GROUP BY copy.code;");
            while (set.next()) {
                System.out.println(set.getString(1) + "\t" + set.getString(2) + "\t"
                        + set.getString(3) + "\t" + set.getString(4));
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
    }

    /**
     * Find books by author 'Стівен Кінг'.
     */
    public void findBookByAuthor() {
        try (Statement st = JDBConnector.getInstance().createStatement()) {
            ResultSet set = st.executeQuery("SELECT book.name, author.name, author.surname"
                    + " FROM book JOIN author"
                    + " ON book.idAuthor=author.idAuthor"
                    + " WHERE author.name LIKE 'Стівен'"
                    + " AND author.surname LIKE 'Кінг'"
                    + " ORDER BY author.surname;");
            System.out.println("\nTitle\t\t\tName\tSurname");
            while (set.next()) {
                System.out.println(set.getString(1) + "\t\t" + set.getString(2) + "\t"
                        + set.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find books by coauthor 'Макс Кідрук'.
     */
    public void findBookByCoauthor() {
        try (Statement st = JDBConnector.getInstance().createStatement()) {
            ResultSet set = st.executeQuery("SELECT book.name, author.name as coauthorName, author.surname AS coauthorSurname\n"
                    + "FROM book JOIN coauthor JOIN author\n"
                    + "ON book.code=coauthor.code\n"
                    + "AND coauthor.idAuthor=author.idAuthor\n"
                    + "WHERE author.name LIKE 'Макс'\n"
                    + "AND author.surname LIKE 'Кідрук'\n"
                    + "ORDER BY author.surname;");
            System.out.println("\nTitle\t\t\tName\tSurname");
            while (set.next()) {
                System.out.println(set.getString(1) + "\t\t\t" + set.getString(2) + "\t"
                        + set.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Show statistic of reader 'Ігор Соколик'.
     */
    public void showReaderStatistic() {
        try (Statement st = JDBConnector.getInstance().createStatement()) {
            ResultSet set = st.executeQuery("SELECT book.name, book.idAuthor, copy.inventoryNumber, orderreader.dataOrder, orderreader.dataReturn\n"
                    + "FROM reader JOIN orderreader JOIN copy JOIN book\n"
                    + "ON reader.idReader=orderreader.idReader\n"
                    + "AND orderreader.inventoryNumber=copy.inventoryNumber\n"
                    + "AND copy.code=book.code\n"
                    + "WHERE reader.name LIKE 'Ігор'\n"
                    + "AND reader.surname LIKE 'Соколик'\n"
                    + "ORDER BY orderreader.dataReturn DESC;");
            System.out.println("\nTitle\t\t\tidAuthor\tinvNumber\tOrderData\tReturnData");
            while (set.next()) {
                System.out.println(set.getString(1) + "\t\t\t" + set.getString(2) + "\t"
                        + set.getString(3) + "\t" + set.getString(4) + "\t" + set.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show how many days reader 'Ігор Соколик' uses library.
     */
    public void howLongReaderUsesLibrary() {
        try (Statement st = JDBConnector.getInstance().createStatement()) {
            ResultSet set = st.executeQuery("SELECT reader.name, reader.surname, (TO_DAYS(NOW()) - TO_DAYS(reader.dateOfCreate)) AS countOfDaysInLibrary\n"
                    + "FROM reader \n"
                    + "WHERE reader.name LIKE 'Ігор'\n"
                    + "AND reader.surname LIKE 'Соколик'\n"
                    + "ORDER BY reader.surname;");
            System.out.println("\nName\t\tSurname\tDays");
            while (set.next()) {
                System.out.println(set.getString(1) + "\t\t" + set.getString(2) + "\t"
                        + set.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
