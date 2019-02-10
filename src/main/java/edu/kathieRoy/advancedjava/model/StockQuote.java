package edu.kathieRoy.advancedjava.model;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * this is a container class the holds the information about a stock for a given date
 */
public class StockQuote {

    private final BigDecimal price;
    private final Calendar date;
    private final String symbol;

    /*
     * ** @param symbol we are taking a symbol in, ie: IBM - but not yet using it.
     */
    public StockQuote(BigDecimal price, Calendar date, String symbol) {
        super();
        this.price = price;
        this.date = date;
        this.symbol = symbol;
    }

    /**
     * get method to return the price of a stock
     *
     * @return the price of the StockQuote object
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * get method to return the date of the stock price
     * @return the date of the StockQuote object
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * get method to return the symbol of the stock
     * @return the symbol of the stock quote object
     */
    public String getSymbol() {
        return symbol;
    }

}
