package com.softserveinc;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Statement st = JDBConnector.getInstance().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
