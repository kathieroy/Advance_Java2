package edu.kathieRoy.advancedjava.service;

import edu.kathieRoy.advancedjava.model.Person;
import edu.kathieRoy.advancedjava.model.StockInterests;

import java.util.List;

/**
 *
 */
public interface PersonService {

    /**
     * Get a list of all people
     *
     * @return a list of Person instances
     * @throws PersonServiceException if a service can not read or write the requested data
     *                                    or otherwise perform the requested operation.
     */
    List<Person> getPerson() throws PersonServiceException;

    /**
     * Add a new person or update an existing Person's data
     *
     * @param person a person object to either update or create
     * @throws PersonServiceException if a service can not read or write the requested data
     *                                    or otherwise perform the requested operation.
     */
    void addOrUpdatePerson(Person person) throws PersonServiceException;

    /**
     * Get a list of all a person's stocks.
     *
     * @return a list of stocks instances
     * @throws PersonServiceException if a service can not read or write the requested data
     *                                    or otherwise perform the requested operation.
     */
    List<StockInterests> getStockInterests(Person person) throws PersonServiceException;

    /**
     * Assign a stockSymbol to a person in StockInterests
     *
     * @param symbol  The stockInterests to assign
     * @param person The person to assign the stockInterests too.
     * @throws PersonServiceException if a service can not read or write the requested data
     *                                    or otherwise perform the requested operation.
     */
    public void addtoStockInterests(String symbol, Person person) throws PersonServiceException;

}
