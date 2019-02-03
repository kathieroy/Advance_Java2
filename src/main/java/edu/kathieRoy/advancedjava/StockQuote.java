package edu.kathieRoy.advancedjava;

import java.math.BigDecimal;

public class StockQuote implements StockService {
    private final BigDecimal quoteAmt = new BigDecimal(1.00);

    private String symbol;


    //constructor to return the amount
    StockQuote(String symbol) {
        super();
        this.symbol = symbol;
    }

    @Override
    public StockQuote getQuote(String symbol) {
        return new StockQuote(symbol);
    }

    @Override
    public BigDecimal getQuoteAmt() {
        return quoteAmt;
    }

}
