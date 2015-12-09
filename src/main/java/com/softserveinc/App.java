package com.softserveinc;

import java.sql.*;
import java.util.Scanner;

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

        boolean isExit = true;
        while(isExit) {
            System.out.println("Choose task: \n" +
                    "1. Finds if book 'Зона покриття' is available.\n" +
                    "2. Find books by author 'Стівен Кінг'.\n" +
                    "3. Find books by coauthor 'Макс Кідрук'. \n" +
                    "4. Show statistic of reader 'Ігор Соколик'.\n" +
                    "5. Show how many days reader 'Ігор Соколик' uses library.\n" +
                    "6. How many book in library, that was made in Ukrainians independence.\n" +
                    "7. How many times the book was taken? (in general).\n" +
                    "8. How many times the book was taken? (by copy).\n" +
                    "9. Find AVG of reading.\n" +
                    "10.Find the most popular book in the period.\n" +
                    "11. Readers who did not return books on time.\n" +
                    "12. Average age of all readers.\n" +
                    "13. Time of using library for each reader.\n" +
                    "14. Average number of appeals to the library.\n" +
                    "15. Average age of all readers who read some book.\n" +
                    "16. Count of copies of some book.\n" +
                    "'exit' - for exit");
            Scanner scanner = new Scanner(System.in);
            switch(scanner.next()) {
                case "6" : queriesByRuslan.countBookInIndependence();
                    break;
                case "7" : queriesByRuslan.findTimesGeneral();
                    break;
                case "8" : queriesByRuslan.findTimesByCopy();
                    break;
                case "9" : queriesByRuslan.findAvgOfReading();
                    break;
                case "10" : queriesByRuslan.findPopularBookByPeriod();
                    break;
                case "11": ReadersDAO.getListOfDebtors();
                    break;
                case "12": ReadersDAO.getAverageAgeOfReaders();
                    break;
                case "13": ReadersDAO.getTimeOfUsingLibrary();
                    break;
                case "14": ReadersDAO.getAverageNumberOfAppeals();
                    break;
                case "15": ReadersDAO.getAverageAgeOfReadersByBook();
                    break;
                case "16": BooksDAO.getCountOfCopies();
                    break;
                default:
                    isExit = false;
            }
        }

        JDBConnector.getInstance().close();
    }
}
