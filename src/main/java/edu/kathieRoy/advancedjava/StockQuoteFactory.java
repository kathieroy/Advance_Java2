package edu.kathieRoy.advancedjava;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @author kathie Use the class to get the object of StockQuote
 */
class StockQuoteFactory {

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
