package edu.kathieRoy.advancedjava.service;


/**
 * @author kathie
 * This factory gets the stock service implementation which can used to get stockquote info.
 *
 */
class StockServiceFactory {

    public static StockService getStockService() {

        return new BasicStockService();

    }

}
