package com.softserveinc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Ihor Sokolyk on 08.12.2015.
 */
public class JDBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;
    private static JDBConnector connector;

    private JDBConnector() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)    ;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Connection getInstance(){
        if (connector == null){
            connector = new JDBConnector();
        }
        return connection;
    }

}
