package com.softserveinc;

import java.sql.*;

public class App 
{
    public static void main( String[] args ) throws SQLException {
        //Ihor's methods


        //Bohdan's methods
        ReadersDAO.getListOfDebtors();

        //Ruslan's methods

        JDBConnector.getInstance().close();
    }
}
