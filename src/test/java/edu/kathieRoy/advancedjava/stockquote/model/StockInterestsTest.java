package edu.kathieRoy.advancedjava.stockquote.model;

import edu.kathieRoy.advancedjava.model.Person;
import edu.kathieRoy.advancedjava.model.StockInterests;
import java.lang.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static edu.kathieRoy.advancedjava.stockquote.model.PersonTest.lastName;
import static org.junit.Assert.*;

public class StockInterestsTest  {

    public static final int id = 100;
    public  static final String symbol = "APPL";
    public static final String firstName = "Roy";
    public  static final String lastName = "Kathie";
    public  static Person person = null;
    public static boolean compareResult;

    @Before
    public void setUp() throws Exception {
        person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        boolean compareResult;
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * helper method to create a StockInterest object  uses the setters
     * @return
     */
    public static StockInterests createStockInterests() {
        StockInterests stockInterests = new StockInterests();
        stockInterests.setId(id);
        stockInterests.setSymbol(symbol);
        stockInterests.setPerson(person);
        return stockInterests;
    }

    /**
     * to test getters and setters, first
     * create the stockInterest Object
     * and then use the getters to insure it is as expected
     */
    @Test
    public void testStockInterestsGettersAndSetters() {
        StockInterests stockInterests = createStockInterests();
        assertEquals("id matches", id, stockInterests.getId());
        assertEquals("symbol matches", symbol, stockInterests.getSymbol());
        assertEquals("person matches", person.getId(), stockInterests.getPerson().getId());
    }

    /**
     * create a new StockInterest Object using the constructor and verify
     * that it is as expected
     */
    @Test
    public void testCreateSpecificStockInterest() {
    StockInterests stockInterests = new StockInterests(person,symbol);
    assertEquals("symbol matches", symbol, stockInterests.getSymbol());
    assertEquals("person matches", person.getId(), stockInterests.getPerson().getId());
}

    /**
     * check that the equals method in StockInterests works
     * by comparing two object that are the same
     */
    @Test
    public void testEqual() {
        StockInterests stockInterests = new StockInterests(person,"APPL");
        assertTrue("checking equals",stockInterests.equals(stockInterests));
        assertTrue(stockInterests.hashCode() == stockInterests.hashCode());

    }
    /**
     * check that the equals method in StockInterests works
     * by comparing two object that are the same
     */
    @Test
    public void testNotEqual() {
        StockInterests stockInterests = new StockInterests(person,"APPL");
        StockInterests stockInterests2 = new StockInterests(person,"test");

        assertFalse("checking not equal",stockInterests.equals(stockInterests2));
        assertFalse(stockInterests.hashCode() == stockInterests2.hashCode());
    }
}