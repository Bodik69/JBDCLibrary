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
        //BooksDAO.getCountOfCopies();
        //Ruslan's methods
		QueriesByRuslan queriesByRuslan = new QueriesByRuslan();
        //queriesByRuslan.countBookInIndependence();
        //queriesByRuslan.findTimesGeneral();
        //queriesByRuslan.findTimesByCopy();
        //queriesByRuslan.findAvgOfReading();
        //queriesByRuslan.findPopularBookByPeriod();

        JDBConnector.getInstance().close();
    }
}
