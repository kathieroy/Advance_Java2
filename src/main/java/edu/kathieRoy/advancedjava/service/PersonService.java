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
     * get all stockInterests for a given person
     * @param person
     * @return
     * @throws PersonServiceException
     */
    List<StockInterests> getStockInterests(Person person) throws PersonServiceException;


}
