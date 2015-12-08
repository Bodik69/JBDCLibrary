package com.softserveinc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ihor Sokolyk on 08.12.2015.
 */
public class QueriesByIhor {
    public void ifBookIsAvailable() throws SQLException {
        Statement st = JDBConnector.getInstance().createStatement();
        ResultSet set = st.executeQuery("SELECT book.*, COUNT(copy.code) AS availableCount\n" +
                "FROM book join copy\n" +
                "ON book.code=copy.code\n" +
                "WHERE book.name LIKE 'Зона Покриття'\n" +
                "AND copy.isInStock=1\n" +
                "GROUP BY copy.code;");
        System.out.println("Code\tName\t\tEdition\tidAuthor\tYear\tpages\tCountOfCopy\tAvailableCount");
//        while (set.next()){
//
//        }
    }
}
