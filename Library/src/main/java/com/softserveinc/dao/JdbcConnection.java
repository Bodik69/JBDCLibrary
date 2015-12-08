package com.softserveinc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Богдан on 08.12.2015.
 */
public class JdbcConnection {
    private static Connection con;
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static JdbcConnection instance;

    private JdbcConnection() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Connection getInstance() {
        if(instance == null) {
            instance = new JdbcConnection();
        }
        return con;
    }
}
