package com.softserveinc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ihor Sokolyk on 08.12.2015.
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
}
