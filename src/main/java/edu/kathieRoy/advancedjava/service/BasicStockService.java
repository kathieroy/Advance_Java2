package edu.kathieRoy.advancedjava.service;

import edu.kathieRoy.advancedjava.model.Interval;
import edu.kathieRoy.advancedjava.model.StockQuote;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A dummy implementation of StockService
 */
public class BasicStockService implements StockService {

    private BigDecimal nextPrice = new BigDecimal(100);
    private Calendar nextStockDate = Calendar.getInstance();

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
        nextStockDate = (Calendar) from.clone();
        while (nextStockDate.compareTo(until) <= 0) {
            quoteList.add(new StockQuote(nextPrice, nextStockDate, symbol));
            nextStockDate.add(Calendar.DAY_OF_YEAR, 1);
            nextPrice = nextPrice.add(new BigDecimal(1));
        }
        return quoteList;
    }

    /**
     * @param symbol   the stock symbol to search for
     * @param from     the date of the first stock quote
     * @param until    the date of the last stock quote
     * @param interval ­ the number of StockQuotes to get. E.g. if Interval.DAY was
     *                 specified, then one StockQuote per day will be returned.
     *                 If HOURLY, then 24 hours in a day mean that 24 stock Quotes per day are returned.
     * @return
     */
    @Override
    public List<StockQuote> getQuote(String symbol, Calendar from,
                                     Calendar until, Interval interval) {
        List<StockQuote> quoteList = new ArrayList<>();
        nextStockDate = (Calendar) from.clone();

        while (nextStockDate.compareTo(until) <= 0) {
            Integer intervalCount = interval.getNumberPerDay();
            switch (interval) {
                case DAY:
                    quoteList.add(new StockQuote(nextPrice, nextStockDate, symbol));
                    nextStockDate.add(Calendar.DAY_OF_YEAR, 1);
                    nextPrice = nextPrice.add(new BigDecimal(1));
                    break;
                case HOUR:
                    while (intervalCount > 0) {
                        quoteList.add(new StockQuote(nextPrice, nextStockDate, symbol));
                        intervalCount--;
                        nextPrice = nextPrice.add(new BigDecimal(1));
                    }
                    break;
                case HALF_HOUR:
                    while (intervalCount > 0) {
                        quoteList.add(new StockQuote(nextPrice, nextStockDate, symbol));
                        intervalCount--;
                        nextPrice = nextPrice.add(new BigDecimal(1));
                    }
                    break;
                case QUARTER_HOUR:
                    while (intervalCount > 0) {
                        quoteList.add(new StockQuote(nextPrice, nextStockDate, symbol));
                        intervalCount--;
                        nextPrice = nextPrice.add(new BigDecimal(1));
                    }
                    break;
                default:
                    break;
            }
            nextStockDate.add(Calendar.DAY_OF_YEAR, 1);
        }
        return quoteList;
    }
}
