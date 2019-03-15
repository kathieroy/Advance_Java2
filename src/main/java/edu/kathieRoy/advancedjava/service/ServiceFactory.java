package edu.kathieRoy.advancedjava.service;


/**
 * @author kathie
 * This factory gets the stock service implementation which can used to get stockquote inf
 * *A factory that returns a<CODE>StockService</CODE>instance.
 * */
public class ServiceFactory {
    /**
     * Prevent instantiations
     */
    private ServiceFactory() {
    }

    /**
     * return an instance of the Database Stock service
     *
     * @return get a <CODE>StockService</CODE> instance
     */
    public static StockService getStockServiceInstance() {
        return new DatabaseStockService();
    }

    /**
     * return an instance of the Database Stock service
     *
     * @return get a <CODE>PersonService</CODE> instance
     */
    public static PersonService getPersonServiceInstance() {
        return new DatabasePersonService();
    }

}
