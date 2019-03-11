package edu.kathieRoy.advancedjava.stockquote.util;

import edu.kathieRoy.advancedjava.util.XMLUtils;
import edu.kathieRoy.advancedjava.xml.Stocks;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for XML utils.
 */
public class XMLUtilsTest {

    private static String SYMBOL = "AOL";
    private static final String pattern = "yyyy-MM-dd hh:mm:ss";


    private static String xmlStocks = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<stocks>\n" +
            "    <stock symbol=\"AOL\" price=\"40.98\" time=\"2015-07-12 00:00:01\"> </stock>\n" +
            "    <stock symbol=\"BCOM\" price=\"55.50\" time=\"2015-09-13 00:00:01\"> </stock>\n" +
            "</stocks>";


    @Test
    public void testUnmarshall() throws Exception {
        Stocks stocks = XMLUtils.unmarshall(xmlStocks, Stocks.class);
        List<Stocks.Stock> stockList = stocks.getStock();
        Date stockDate = null;
        for (Stocks.Stock stock : stockList) {
            validateStocks(stock);
            break;
        }
    }

    @Test
    public void testUnmarshallWithSchemaValidation()throws Exception {
        Stocks stocks = XMLUtils.unmarshall(xmlStocks, Stocks.class, "/xml/stock_info.xsd");
        List<Stocks.Stock> stockList = stocks.getStock();
        Date stockDate = null;
        for (Stocks.Stock stock : stockList) {
            validateStocks(stock);
            break;
        }
     }

    @Test
    public void testMarshall() throws Exception {
        Stocks stocks = XMLUtils.unmarshall(xmlStocks, Stocks.class, "/xml/stock_info.xsd");
        String xml = XMLUtils.marshall(stocks);
        // input xml should be the same as output xml
        assertEquals("XML out is correct", xml.trim() ,xmlStocks.trim());
    }

    private void validateStocks(Stocks.Stock stock ) {
        assertTrue("Symbol is correct", stock.getSymbol().equals(SYMBOL));
    }


}
