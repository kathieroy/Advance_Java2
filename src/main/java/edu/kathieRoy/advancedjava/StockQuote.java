package edu.kathieRoy.advancedjava;

import java.math.BigDecimal;
import java.util.Date;

public class StockQuote {

    private final BigDecimal quoteAmt = new BigDecimal(1.00);

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

    public BigDecimal getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public String getSymbol() {
        return symbol;
    }
}
