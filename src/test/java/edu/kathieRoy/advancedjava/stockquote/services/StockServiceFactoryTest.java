package edu.kathieRoy.advancedjava.stockquote.services;

import edu.kathieRoy.advancedjava.service.StockService;
import edu.kathieRoy.advancedjava.service.StockServiceFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * JUnit test for <CODE>StockServiceFactory</CODE>
 */
public class StockServiceFactoryTest {

    @Test
    public void testGetInstance() {
        StockService stockService = StockServiceFactory.getInstance();
        assertNotNull(stockService);
    }
}
