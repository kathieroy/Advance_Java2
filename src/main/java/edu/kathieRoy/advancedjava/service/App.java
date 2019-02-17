package edu.kathieRoy.advancedjava.service;

import edu.kathieRoy.advancedjava.model.Interval;
import edu.kathieRoy.advancedjava.model.StockQuote;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 * Application that will create a one instance of a StockQuote and then
 * a list of StockQuotesfor a particular Date Range
 */
public class App {

    private static final String pattern = "yyyyMMdd";
    private static final String badDateMessage = "Sorry - need two valid dates - can't give Stock Quotes";

    public static void main(String[] args) {

        StockService stockService = StockServiceFactory.getStockService();
        String date1 = "";
        String date2 = "";
        Calendar startDate = Calendar.getInstance();
        Calendar stopDate = Calendar.getInstance();
        // if dates are not passed in, request the user to input them
        // if the dates ARE passed in, validate them in the conversion to Calendar date
        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            do {
                try {
                    System.out.printf("Enter Start Date (%s): ", pattern);
                    date1 = scanner.next();
                    //convert the string date to a Calendar object.
                    startDate = convertDate(date1);
                } catch (ParseException e) {
                    date1 = "";
                }
            } while (date1.isEmpty());


            do {
                try {
                    System.out.printf("Enter Stop Date (%s): ", pattern);
                    date2 = scanner.next();
                    //convert the string date to a Calendar object.
                    stopDate = convertDate(date2);
                } catch (ParseException e) {
                    date2 = "";
                }
            } while (date2.isEmpty());
        } else {
            if (args.length == 2) {
                date1 = args[0];
                try {
                    startDate = convertDate(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.printf(badDateMessage);
                    System.exit(0);
                }
                date2 = args[1];
                try {
                    startDate = convertDate(date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.printf(badDateMessage);
                    System.exit(0);
                }
            } else System.out.printf(badDateMessage);
            System.exit(0);
        }


/**
 * (assignment 2) using the StockService that was developed, return one stock Quote
 */
        System.out.println("First, here is one StockQuote: ");

        StockQuote quote = stockService.getQuote("APPL");
        System.out.print("Symbol " + quote.getSymbol());
        System.out.print(" Date " + quote.getDate().getTime());
        System.out.println(" Price " + quote.getPrice());
/**
 * (asignment 3) passing in a startDate and a stopDate, retrieve all stock Quotes for each day in the range, including the start/stop date
 */
        System.out.println("Now, here is the list of StockQuotes found for the Symbol/Date Range: ");
        List<StockQuote> stockQuoteList = stockService.getQuote("APPL", startDate, stopDate);
        System.out.println("Symbol APPL has " + stockQuoteList.size() + " rows in it");
        /**
         * List out the different stock quotes that were returned in prior list retrieval
         */
        for (StockQuote stockQuote : stockQuoteList) {
            System.out.println("Symbol: " + stockQuote.getSymbol() + " Date: " + stockQuote.getDate().getTime() + " Price: " + stockQuote.getPrice());
        }

        /**
         * (assignment 4) passing in a startDate,stopDate, and a valid Interval period as defined below, retrieve all stock Quotes for each Interval per day in the range, including the start/stop date
         * possible Intervals available:  DAY,HOUR,HALF_HOUR,QUARTER_HOUR.
         */
        System.out.println("Now, here is the list of StockQuotes found for each Interval period per day in the Symbol/Date Range: ");
        List<StockQuote> stockQuoteIntervalList = stockService.getQuote("APPL", startDate, stopDate, Interval.HOUR);
        System.out.println("Symbol APPL, Interval HOUR (24 * #Days)  has " + stockQuoteIntervalList.size() + " rows in it");
        /**
         * List out the different stock quotes that were returned in prior list retrieval
         */
        Integer intervalCounter = 0;
        for (StockQuote stockQuote : stockQuoteIntervalList) {
            intervalCounter++;
            System.out.println("Interval #: " + intervalCounter +
                    " Symbol: " + stockQuote.getSymbol() + " Date: " + stockQuote.getDate().getTime() + " Price: " + stockQuote.getPrice());
            if (intervalCounter == Interval.HOUR.getNumberPerDay())
                intervalCounter = 0;
        }
    }

    /**
     * Convert the String date (entered or passed in)
     * to a Calendar date object
     *
     * @param date String to be converted to Calendar object
     * @return returns Calendar object
     */
    public static Calendar convertDate(String date) throws ParseException {

        Calendar cDate = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            cDate.setTime(sdf.parse(date));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return cDate;
    }
}
