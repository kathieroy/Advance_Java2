package edu.kathieRoy.advancedjava.service;

import edu.kathieRoy.advancedjava.model.StockQuote;

/**
 * Hello world!
 */
class App {

    public static void main(String[] args) {

        StockService stockService = StockServiceFactory.getStockService();

        StockQuote quote = stockService.getQuote("APPL");
        System.out.println("Symbol " + quote.getSymbol());
        System.out.println("Date " + quote.getDate());
        System.out.println("Price " + quote.getPrice());
    }
}
