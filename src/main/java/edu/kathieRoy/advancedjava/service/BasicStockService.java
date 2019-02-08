package edu.kathieRoy.advancedjava.service;

import edu.kathieRoy.advancedjava.model.StockQuote;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * A dummy implementation of StockService
 */
public class BasicStockService implements StockService {
    public BasicStockService() {
        super();

    }

    @Override
    public StockQuote getQuote(String symbol) {
        return new StockQuote(new BigDecimal(100), Calendar.getInstance().getTime(), "APPL");
    }
  /*  @Override
    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until){
        BigDecimal[] priceArr = {new BigDecimal(100),new BigDecimal(200),new BigDecimal(300),new BigDecimal(400),new BigDecimal(500)};
        Date[]dateArr = {Calendar.getInstance().getTime(),Calendar.getInstance().getTime(),Calendar.getInstance().getTime(),Calendar.getInstance().getTime(),Calendar.getInstance().getTime() };
        StockQuote stName = new StockQuote(priceArr[],dateArr[],"APPL");
        return StockQuote.getStockList(symbol,from,until);
    }
*/
}
