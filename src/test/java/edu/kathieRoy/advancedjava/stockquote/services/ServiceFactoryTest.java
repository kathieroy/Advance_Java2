package edu.kathieRoy.advancedjava.stockquote.services;

import edu.kathieRoy.advancedjava.service.PersonService;
import edu.kathieRoy.advancedjava.service.StockService;
import edu.kathieRoy.advancedjava.service.ServiceFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * JUnit test for <CODE>ServiceFactory</CODE>
 * retrieving the StockService Instance
 */
public class ServiceFactoryTest {

    @Test
    public void testGetStockServiceInstance() {
        StockService stockService = ServiceFactory.getStockServiceInstance();
        assertNotNull(stockService);
    }


    /**
     * JUnit test for <CODE>ServiceFactory</CODE>
     * retrieving the PersonService Instance
     */
    @Test
    public void testGetPersonServiceInstance() {
        PersonService personService = ServiceFactory.getPersonServiceInstance();
        assertNotNull(personService);
    }
}
