package edu.kathieRoy.advancedjava;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * A dummy implementation of StockService
 */
public class BasicStockService implements StockService {

    @Override
    public StockQuote getQuote(String symbol) {
        return new StockQuote(new BigDecimal(100), Calendar.getInstance().getTime(),"APPL");
    }
}
