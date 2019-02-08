package edu.kathieRoy.advancedjava.service;


/**
 * @author kathie Use the class to get the object of StockQuote
 */
class StockServiceFactory {

    public static StockService getStockService() {

        return new BasicStockService();


        /*
        return new StockService() {

            @Override
            public StockQuote getQuote(String symbol) {
                return StockQuote(new BigDecimal(100)Calendar.getInstance().getTime(), "APPL")
            }
        };
        */

    }

}
