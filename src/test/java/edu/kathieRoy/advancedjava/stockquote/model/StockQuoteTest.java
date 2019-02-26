package edu.kathieRoy.advancedjava.stockquote.model;

import edu.kathieRoy.advancedjava.model.StockQuote;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * JUnit test for StockQuote class
 */
public class StockQuoteTest {

    private BigDecimal price;
    private Date date;
    private String symbol;
    private StockQuote stockQuote;

    @Before
    public void setUp() {
        price = new BigDecimal(100);
        symbol = "APPL";
        date = Calendar.getInstance().getTime();
        stockQuote = new StockQuote(price, date, symbol);
    }


    @Test
    public void testGetStockQuoteWithCalendar(){
        Calendar cDate = Calendar.getInstance();
        stockQuote = new StockQuote(price, cDate, symbol);
        assertEquals("Share date is correct", date, stockQuote.getDate());
    }


    @Test
    public void testGetPrice() {
        assertEquals("Share price is correct", price, stockQuote.getPrice());
    }

    @Test
    public void testGetDate() {
        assertEquals("Share date is correct", date, stockQuote.getDate());
    }

    @Test
    public void testGetSymbol() {
        assertEquals("Symbol  is correct", symbol, stockQuote.getSymbol());
    }
}
