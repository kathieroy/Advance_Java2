package edu.kathieRoy.advancedjava.stockquote.services;


import edu.kathieRoy.advancedjava.model.Interval;
import edu.kathieRoy.advancedjava.model.StockQuote;
import edu.kathieRoy.advancedjava.service.DatabaseStockService;
import edu.kathieRoy.advancedjava.service.StockServiceException;
import edu.kathieRoy.advancedjava.util.DatabaseInitializationException;
import edu.kathieRoy.advancedjava.util.DatabaseUtils;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for the DatabaseStockService
 */
public class DatabaseStockServiceTest {
    private BigDecimal price;
    private Calendar from;
    private Calendar until;
    private String symbol;
    public static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    DatabaseStockService databaseStockService = null;

@Before
public void setUp() throws DatabaseInitializationException {
    DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
    databaseStockService = new DatabaseStockService();
}

    /**
     * a simple test to determine if we can get one stockQuote using a
     * symbol (only) that we know exists in the DB.
     * @throws Exception
     */
    @Test
    public void testGetQuote() throws Exception {
        DatabaseStockService databaseStockService = new DatabaseStockService();
        String symbol = "APPL";
        StockQuote stockQuote = databaseStockService.getQuote(symbol);
        assertNotNull("Verify we can get a stock quote from the db", stockQuote);
        assertEquals("Make sure the symbols match", symbol, stockQuote.getSymbol());
    }


    /**
     * a simple test to determine if, when we use a symbol that we know is NOT in the DB,
     * we will get a StockServiceException that presents the message
     * "There is no stock data for" + symbol
     * @throws Exception
     */
    @Test(expected = StockServiceException.class)
    public void testGetQuoteNotThere() throws Exception {
        String symbol = "KROY";
        StockQuote stockQuote = databaseStockService.getQuote(symbol);
    }


    /**
     * to check if anything has come back in a list,
     * use assertFalse along with isEmpty()
     * if it is empty, then a true condition occurs, causing the test to fail.
     * @throws Exception
     */
    @Test
    public void testGetQuoteDates() throws Exception {
        String symbol = "APPL";
        String fromStringDate = "2018-01-01 00:00:01";
        String untilStringDate = "2018-06-30 00:00:01";
        Calendar fromCalendar = convertStringToCalendarDate(fromStringDate);
        Calendar untilCalendar = convertStringToCalendarDate(untilStringDate);
        List<StockQuote> stockQuoteList = databaseStockService.getQuote(symbol,fromCalendar,untilCalendar);
        assertFalse("Verify we can get a stock quote from the db", stockQuoteList.isEmpty());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testGetQuoteInterval() throws Exception {
        String symbol = "APPL";
        String fromStringDate = "2018-01-01 00:00:01";
        String untilStringDate = "2018-06-30 00:00:01";
        Calendar fromCalendar = convertStringToCalendarDate(fromStringDate);
        Calendar untilCalendar = convertStringToCalendarDate(untilStringDate);
        List<StockQuote> stockQuoteList = databaseStockService.getQuote(symbol,fromCalendar,untilCalendar, Interval.HALF_HOUR);
        assertFalse("Verify we can get a stock quote from the db", stockQuoteList.isEmpty());
    }

    /**
     * Convert the String date (entered or passed in)
     * to a Calendar date object
     *
     * @param date String to be converted to Calendar object
     * @return returns Calendar object
     */
    public static Calendar convertStringToCalendarDate (String date) throws ParseException {

        Calendar cDate = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            cDate.setTime(sdf.parse(date));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return cDate;
    }
}
