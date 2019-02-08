package edu.kathieRoy.advancedjava;

import edu.kathieRoy.advancedjava.model.StockQuote;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class StockQuoteTest {

    @Test
    public void testConstruction() {

        BigDecimal price = new BigDecimal(100);
        Date time = Calendar.getInstance().getTime();
        String symbol = "APPL";

        StockQuote stockQuote = new StockQuote(price, time, symbol);
        assertEquals("verify price", price, stockQuote.getPrice());
        assertEquals("verify time", time, stockQuote.getDate());
        assertEquals("verify symbol", symbol, stockQuote.getSymbol());
    }
}
