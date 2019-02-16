package edu.kathieRoy.advancedjava.service;

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
class App {

    private static final String pattern = "yyyyMMdd";

    public static void main(String[] args) {

        StockService stockService = StockServiceFactory.getStockService();
        String date1;
        String date2;
        // if dates are not passed in, request the user to input them
        if (args.length == 0) {
            Scanner sc = new Scanner(System.in);
            System.out.printf("Enter Start Date (%s): ", pattern);
            date1 = sc.next();

            System.out.printf("Enter Stop Date (%s): ", pattern);
            date2 = sc.next();
        } else {
            date1 = args[0];
            date2 = args[1];
        }
        //convert the string date to a Calendar object.
        Calendar startDate = convertDate(date1);
        Calendar stopDate = convertDate(date2);

        System.out.println("First, here is one StockQuote: ");

        StockQuote quote = stockService.getQuote("APPL");
        System.out.print("Symbol " + quote.getSymbol());
        System.out.print(" Date " + quote.getDate().getTime());
        System.out.println(" Price " + quote.getPrice());

        System.out.println("Now, here is the number of rows in the list of StockQuotes found for the Symbol/Date Range: ");
        List<StockQuote> stockQuoteList = stockService.getQuote("APPL", startDate, stopDate);
        System.out.println("Symbol APPL has " + stockQuoteList.size() + " rows in it");
        for (StockQuote stockQuote : stockQuoteList) {
            System.out.println("Symbol: " + stockQuote.getSymbol() + " Date: " + stockQuote.getDate().getTime() + " Price: " + stockQuote.getPrice());
        }
    }

    /**
     * Convert the String date (entered or passed in)
     * to a Calendar date object
     *
     * @param date String to be converted to Calendar object
     * @return returns Calendar object
     */
    private static Calendar convertDate(String date) {

        Calendar cDate = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            cDate.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cDate;
    }
}
