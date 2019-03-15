package edu.kathieRoy.advancedjava.stockquote.model;

import edu.kathieRoy.advancedjava.model.Interval;
import edu.kathieRoy.advancedjava.model.StockQuery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for StockQuery Class
 */
public class StockQueryTest {

    @Test
    public void testBasicConstruction() throws Exception{
        String symbol = "APPL";
        String interval = Interval.DAY.toString();
        StockQuery stockQuery = new StockQuery(symbol,"2018-01-01 00:00:01","2018-01-01 00:00:01",Interval.DAY );
        assertEquals("Verify construction", symbol, stockQuery.getSymbol());
    }

}
