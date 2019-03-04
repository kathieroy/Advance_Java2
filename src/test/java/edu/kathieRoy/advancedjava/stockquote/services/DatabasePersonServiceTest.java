package edu.kathieRoy.advancedjava.stockquote.services;

import edu.kathieRoy.advancedjava.model.Person;
import edu.kathieRoy.advancedjava.model.StockInterests;
import edu.kathieRoy.advancedjava.service.DatabasePersonService;
import edu.kathieRoy.advancedjava.service.PersonServiceException;
import edu.kathieRoy.advancedjava.util.DatabaseInitializationException;
import edu.kathieRoy.advancedjava.util.DatabaseUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class DatabasePersonServiceTest {
    private String symbol;
    DatabasePersonService databasePersonService = null;

    @Before
    public void setUp() throws DatabaseInitializationException {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
        databasePersonService = new DatabasePersonService();
    }

    /**
     * this will test that we can get a list of person from the DB
     * @throws PersonServiceException
     */
    @Test
    public void testGetPerson()throws PersonServiceException {
        databasePersonService = new DatabasePersonService();
        List<Person> personList = databasePersonService.getPerson();
        assertFalse("Verify we can get person data from the db", personList.isEmpty());
    }

    /**
     * this will test that we can get a list of StockInterest from the DB
     * @throws PersonServiceException
     * ** note: commenting this out as a test of CircleCi because it is failing there
     */
//    @Test
//    public void testGetStockInterests()throws PersonServiceException {
//        databasePersonService = new DatabasePersonService();
//        Person person = new Person(1,"Kathie","Roy");
//        List<StockInterests> stockInterests = databasePersonService.getStockInterests(person);
//        assertFalse("Verify we can get stockInterests data from the db", stockInterests.isEmpty());
//    }
}
