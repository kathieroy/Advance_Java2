package edu.kathieRoy.advancedjava;

import java.math.BigDecimal;

interface StockService {
    /**
     * Return the current price for a share of stock for the given symbol
     *
     * @param symbol the stock symbol of the company you want a quote for.
     *               e.g. APPL for APPLE
     * @return a <CODE>StockQuote </CODE> instance
     */
    public StockQuote getQuote(String symbol);



}
