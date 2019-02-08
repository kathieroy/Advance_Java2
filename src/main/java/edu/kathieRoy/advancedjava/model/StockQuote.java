package edu.kathieRoy.advancedjava.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * this is a container class the holds the information about a stock for a given date
 */
public class StockQuote {

    private BigDecimal price;
    private Date date;
    private String symbol;

    /**
     * @param symbol we are taking a symbol in, ie: IBM - but not yet using it.
     */
    public StockQuote(BigDecimal price, Date date, String symbol) {
        super();
        this.price = price;
        this.date = date;
        this.symbol = symbol;
    }

    /**
     * get method to return the price of a stock
     *
     * @return
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * get method to return the date of the stock price
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     * get method to return the symbol of the stock
     * @return
     */
    public String getSymbol() {
        return symbol;
    }
}
