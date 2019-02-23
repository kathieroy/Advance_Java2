package edu.kathieRoy.advancedjava;

import edu.kathieRoy.advancedjava.model.Interval;
import edu.kathieRoy.advancedjava.model.StockQuote;
import edu.kathieRoy.advancedjava.service.App;
import edu.kathieRoy.advancedjava.service.BasicStockService;
import edu.kathieRoy.advancedjava.service.StockService;
import edu.kathieRoy.advancedjava.service.StockServiceFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class StockQuoteTest extends App {
    BigDecimal price = new BigDecimal(100);
    Calendar time = Calendar.getInstance();
    String symbol = "APPL";
    private static final String badDateMessage = "Sorry - need two valid dates - can't give Stock Quotes";
    String lastSysOutmessage;

    /**
     * test the main app sending 2 valid date arguments
     */
    @Before
    public void injectLastSysOutMessage() {
        App.setSysOutMessage((val) -> lastSysOutmessage = val);
    }

    @Test
    public void testArgCount1() {
        App.main(new String[]{"20180101"});
        assertEquals("verify arguments", badDateMessage, lastSysOutmessage);
    }

    @Test
    public void testArgCount2() {
        App.main(new String[]{"20180101", "20180130"});
        assertNotEquals("verify arguments", badDateMessage, lastSysOutmessage);
    }

    @Test
    public void testArgValid2() {
        App.main(new String[]{"20180101", "badDate"});
        assertEquals("verify arguments", badDateMessage, lastSysOutmessage);
    }

    @Test
    public void testArgValid1() {
        App.main(new String[]{"badDate", "20180130"});
        assertEquals("verify arguments", badDateMessage, lastSysOutmessage);
    }


    @Test
    public void testBasicStockService() {
        BasicStockService stockService = new BasicStockService();
        assertNotNull(stockService);
    }

    @Test
    public void testStockService() {
        StockService stockService = StockServiceFactory.getStockService();
        assertNotNull(stockService);
    }

    /**
     *
     */
    @Test
    public void testConstruction() {
        StockQuote stockQuote = new StockQuote(price, time, symbol);
        assertEquals("verify price", price, stockQuote.getPrice());
        assertEquals("verify time", time, stockQuote.getDate());
        assertEquals("verify symbol", symbol, stockQuote.getSymbol());
    }

    /**
     *  this test will check if getQuote pulls in a stockQuote with the right values
     */
    @Test
    public void testGetQuote() {
        BasicStockService stockService = new BasicStockService();
        StockQuote stockQuote = stockService.getQuote("APPL");
        assertEquals("verify symbol", symbol, stockQuote.getSymbol());
    }

    /**
     * this test will simply determine if the right number of stockQuotes are returned for the given date range
     * because start date and stop date are inclusive, 20180101, 20180102 is two days
     */
    @Test
    public void testGetQuoteList() {
        BasicStockService stockService = new BasicStockService(); // would I rather do this in a before?
        Calendar startDate = Calendar.getInstance();
        Calendar stopDate = Calendar.getInstance();
        stopDate.add(Calendar.DAY_OF_YEAR, 2);  // + 2 days
        List<StockQuote> stockQuoteList = stockService.getQuote("APPL", startDate, stopDate);
        assertEquals("Verify list length", 3, stockQuoteList.size());
    }

    /**
     * this will test whether execution of getQuote with interval returns correct list length
     */
    @Test
    public void testGetQuoteListDay() {
        BasicStockService stockService = new BasicStockService();
        Calendar startDate = Calendar.getInstance();
        Calendar stopDate = Calendar.getInstance();
        stopDate.add(Calendar.DAY_OF_YEAR, 2);  // + 2 days
        List<StockQuote> stockQuoteList = stockService.getQuote("APPL", startDate, stopDate, Interval.DAY);
        assertEquals("Verify list length", 2, stockQuoteList.size());
    }

    /**
     * this will test whether execution of getQuote with interval returns correct list length
     */
    @Test
    public void testGetQuoteListHour() {
        BasicStockService stockService = new BasicStockService();
        Calendar startDate = Calendar.getInstance();
        Calendar stopDate = Calendar.getInstance();
        stopDate.add(Calendar.DAY_OF_YEAR, 2);  // + 2 days
        List<StockQuote> stockQuoteList = stockService.getQuote("APPL", startDate, stopDate, Interval.HOUR);
        assertEquals("Verify list length", 72, stockQuoteList.size());
    }

    /**
     * this will test whether execution of getQuote with interval returns correct list length
     */
    @Test
    public void testGetQuoteListHalfHour() {
        BasicStockService stockService = new BasicStockService();
        Calendar startDate = Calendar.getInstance();
        Calendar stopDate = Calendar.getInstance();
        stopDate.add(Calendar.DAY_OF_YEAR, 2);  // + 2 days
        List<StockQuote> stockQuoteList = stockService.getQuote("APPL", startDate, stopDate, Interval.HALF_HOUR);
        assertEquals("Verify list length", 144, stockQuoteList.size());
    }

    /**
     * this will test whether execution of getQuote with interval returns correct list length
     */
    @Test
    public void testGetQuoteListQtrHour() {
        BasicStockService stockService = new BasicStockService();
        Calendar startDate = Calendar.getInstance();
        Calendar stopDate = Calendar.getInstance();
        stopDate.add(Calendar.DAY_OF_YEAR, 2);  // + 2 days
        List<StockQuote> stockQuoteList = stockService.getQuote("APPL", startDate, stopDate, Interval.QUARTER_HOUR);
        assertEquals("Verify list length", 288, stockQuoteList.size());
    }
}
