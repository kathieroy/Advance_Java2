package edu.kathieRoy.advancedjava.service;

import edu.kathieRoy.advancedjava.model.Interval;
import edu.kathieRoy.advancedjava.model.StockQuote;
import edu.kathieRoy.advancedjava.model.SysOutMessage;

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
    private static SysOutMessage sysOutMessage = (val) -> System.out.println(val);

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
            startDate = getCalendar(startDate, scanner, "Enter Start Date (%s): ");
            stopDate = getCalendar(stopDate, scanner, "Enter Stop Date (%s): ");
        } else {
            if (args.length == 2) {
                try {
                    startDate = getArgDate(args, startDate, 0);
                } catch (ParseException e) {
                    return;
                }
                try {
                    stopDate = getArgDate(args, stopDate, 1);
                } catch (ParseException e) {
                    return;
                }
            } else {
                sysOutMessage.println(badDateMessage);
                return;
            }
            //       System.exit(0);
        }
        assignment2(stockService);
        assignment3(stockService, startDate, stopDate);
        assignment4(stockService, startDate, stopDate);
    }

    /**
     * (assignment 4) passing in a startDate,stopDate, and a valid Interval period as defined below, retrieve all stock Quotes for each Interval per day in the range, including the start/stop date
     * possible Intervals available:  DAY,HOUR,HALF_HOUR,QUARTER_HOUR.
     */
    private static void assignment4(StockService stockService, Calendar startDate, Calendar stopDate) {

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
     * (asignment 3) passing in a startDate and a stopDate, retrieve all stock Quotes for each day in the range, including the start/stop date
     */
    private static void assignment3(StockService stockService, Calendar startDate, Calendar stopDate) {

        System.out.println("Now, here is the list of StockQuotes found for the Symbol/Date Range: ");
        List<StockQuote> stockQuoteList = stockService.getQuote("APPL", startDate, stopDate);
        System.out.println("Symbol APPL has " + stockQuoteList.size() + " rows in it");
        /**
         * List out the different stock quotes that were returned in prior list retrieval
         */
        for (StockQuote stockQuote : stockQuoteList) {
            System.out.println("Symbol: " + stockQuote.getSymbol() + " Date: " + stockQuote.getDate().getTime() + " Price: " + stockQuote.getPrice());
        }
    }

    /**
     * (assignment 2) using the StockService that was developed, return one stock Quote
     */
    private static void assignment2(StockService stockService) {

        System.out.println("First, here is one StockQuote: ");
        StockQuote quote = stockService.getQuote("APPL");
        System.out.print("Symbol " + quote.getSymbol());
        System.out.print(" Date " + quote.getDate().getTime());
        System.out.println(" Price " + quote.getPrice());
    }

    private static Calendar getArgDate(String[] args, Calendar startDate, int i) throws ParseException {
        String date1;
        date1 = args[i];
        try {
            startDate = convertDate(date1);
        } catch (ParseException e) {
            sysOutMessage.println(badDateMessage);
            throw e;
        }
        return startDate;
    }

    private static Calendar getCalendar(Calendar startDate, Scanner scanner, String s) {
        String date1;
        do {
            try {
                System.out.printf(s, pattern);
                date1 = scanner.next();
                //convert the string date to a Calendar object.
                startDate = convertDate(date1);
            } catch (ParseException e) {
                date1 = "";
            }
        } while (date1.isEmpty());
        return startDate;
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

    /**
     * found online - a way to test the main App
     */
    protected static void setSysOutMessage(SysOutMessage val) {
        sysOutMessage = val;
    }
}
