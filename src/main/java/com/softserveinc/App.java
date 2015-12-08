package com.softserveinc;

import java.sql.*;

public class App 
{
    public static void main( String[] args ) throws SQLException {
        //Ihor's methods
        QueriesByIhor query = new QueriesByIhor();
//        query.ifBookIsAvailable();
//        query.findBookByAuthor();
//        query.findBookByCoauthor();
//        query.showReaderStatistic();
//        query.howLongReaderUsesLibrary();

        //Bohdan's methods
        //ReadersDAO.getListOfDebtors();
        //ReadersDAO.getAverageAgeOfReaders();
        //ReadersDAO.getTimeOfUsingLibrary();
        //ReadersDAO.getAverageNumberOfAppeals();
        //ReadersDAO.getAverageAgeOfReadersByBook();
        //Ruslan's methods

        JDBConnector.getInstance().close();
    }
}
