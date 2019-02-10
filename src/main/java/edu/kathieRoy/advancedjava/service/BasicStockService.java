package edu.kathieRoy.advancedjava.service;

import edu.kathieRoy.advancedjava.model.StockQuote;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A dummy implementation of StockService
 */
public class BasicStockService implements StockService {

    public BasicStockService() {
        super();
    }

    /**
     * @param symbol the stock symbol of the company you want a quote for e.g. APPL for APPLE
     * @return a new StockQuote object
     */
    @Override
    public StockQuote getQuote(String symbol) {
        return new StockQuote(new BigDecimal(100), Calendar.getInstance(), "APPL");
    }

    /**
     *
     * @param symbol the stock symbol to search for
     * @param from the date of the first stock quote
     * @param until the date of the last stock quote
     * @return a list of the StockQuotes between start and stop dates
     */
    @Override
    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until){
        List<StockQuote> quoteList = new ArrayList<>();
        BigDecimal nextPrice = new BigDecimal(100);

        Calendar nextStockDate = from;
        while (nextStockDate.compareTo(until) < 0 && nextStockDate.compareTo(from) >= 0) {
            quoteList.add(new StockQuote(nextPrice, nextStockDate, symbol));
            nextStockDate.add(Calendar.DAY_OF_YEAR, 1);
            nextPrice = nextPrice.add(new BigDecimal(1));
        }
        return quoteList;
    }

}
