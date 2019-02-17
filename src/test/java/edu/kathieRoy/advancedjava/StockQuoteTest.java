package edu.kathieRoy.advancedjava;

import edu.kathieRoy.advancedjava.model.Interval;
import edu.kathieRoy.advancedjava.model.StockQuote;
import edu.kathieRoy.advancedjava.service.BasicStockService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StockQuoteTest {

    // not sure how to do this
    // @Before
    //BasicStockService stockService = new BasicStockService();

    /**
     *
     */
    @Test
    public void testConstruction() {

        BigDecimal price = new BigDecimal(100);
        Calendar time = Calendar.getInstance();
        String symbol = "APPL";

        StockQuote stockQuote = new StockQuote(price, time, symbol);
        assertEquals("verify price", price, stockQuote.getPrice());
        assertEquals("verify time", time, stockQuote.getDate());
        assertEquals("verify symbol", symbol, stockQuote.getSymbol());
    }

    /**
     *  this test will simply determine if the right number of stockQuotes are returned for the given date range
     *  because start date and stop date are inclusive, 20180101, 20180102 is two days
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
     *
     */
    @Test
    public void testGetQuoteListInterval() {
        BasicStockService stockService = new BasicStockService();
        Calendar startDate = Calendar.getInstance();
        Calendar stopDate = Calendar.getInstance();
        stopDate.add(Calendar.DAY_OF_YEAR, 2);  // + 2 days
        List<StockQuote> stockQuoteList = stockService.getQuote("APPL", startDate, stopDate, Interval.HOUR);
        assertEquals("Verify list length", 72, stockQuoteList.size());
    }
}
