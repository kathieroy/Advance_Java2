package edu.kathieRoy.advancedjava;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

/**
 * Unit test for Assignment 2 - StockService
 */
public class StockTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testStockService() {
        StockQuote quote = new StockQuote("IBM");
        assertEquals("verify quoteAmt = 1.00", (new BigDecimal(1.00)), quote.getQuoteAmt());
    }
}
