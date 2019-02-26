package edu.kathieRoy.advancedjava.service;


/**
 * @author kathie
 * This factory gets the stock service implementation which can used to get stockquote inf
 * *A factory that returns a<CODE>StockService</CODE>instance.
 * */
public class StockServiceFactory {
    /**
     * Prevent instantiations
     */
    private StockServiceFactory() {
    }

    /**
     * return an instance of the Database Stock service
     *
     * @return get a <CODE>StockService</CODE> instance
     */
    public static StockService getInstance() {
        return new DatabaseStockService();
    }

}
