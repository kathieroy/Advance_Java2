package edu.kathieRoy.advancedjava.service;

/**
 * A factory that returns a <CODE>PersonService</CODE> instance.
 */
public class PersonServiceFactory {

    /**
     * Prevent instantiations
     */
    private PersonServiceFactory() {
    }

    /**
     *
     * @return get a <CODE>StockService</CODE> instance
     */
    public static PersonService getInstance() {
        return new DatabasePersonService();
    }

}
