package edu.kathieRoy.advancedjava;

import java.math.BigDecimal;

@SuppressWarnings("SpellCheckingInspection")
public class StockQuote implements StockService {
    private final BigDecimal quoteAmt = new BigDecimal(1.00);

    // private String symbol;

    /**
     * @param symbol we are taking a symbol in, ie: IBM - but not yet using it.
     */
    //constructor to return the amount
    StockQuote(String symbol) {
        super();
        //     this.symbol = symbol;
    }

    /**
     *
     * @param symbol the stock symbol of the company you want a quote for.
     *               e.g. APPL for APPLE
     * @return returns the StockQuote object
     */
    @Override
    public StockQuote getQuote(String symbol) {
        return new StockQuote(symbol);
    }

    /**
     *
     * @return the quote amount
     */
    @Override
    public BigDecimal getQuoteAmt() {
        return quoteAmt;
    }

}
