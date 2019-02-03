package edu.kathieRoy.advancedjava;

/**
 * @author kathie Use the class to get the object of StockQuote
 */
class StockQuoteFactory {
    /**
     * @param args this program will return the Stock quote object,
     *             which includes the quote amount for IBM stocks
     */
    public static void main(String[] args) {

        StockQuote quote = new StockQuote("IBM");
        System.out.println("stock quote = $" + quote.getQuoteAmt());
    }
}
