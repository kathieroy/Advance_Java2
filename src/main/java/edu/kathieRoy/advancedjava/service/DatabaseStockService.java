package edu.kathieRoy.advancedjava.service;

import edu.kathieRoy.advancedjava.model.Interval;
import edu.kathieRoy.advancedjava.model.StockData;
import edu.kathieRoy.advancedjava.model.StockQuote;
import edu.kathieRoy.advancedjava.util.DatabaseConnectionException;
import edu.kathieRoy.advancedjava.util.DatabaseUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * An implementation of the StockService interface that gets
 * stock data from a database.
 */
public class DatabaseStockService implements StockService {
    private Calendar nextStockStartDate = Calendar.getInstance();
    //  public static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public DatabaseStockService() {
        super();
    }

    /**
     * Return the current price for a share of stock  for the given symbol
     *
     * @param symbol the stock symbol of the company you want a quote for.
     *               e.g. APPL for APPLE
     * @return a  <CODE>BigDecimal</CODE> instance
     * @throws StockServiceException if using the service generates an exception.
     *                               If this happens, trying the service may work, depending on the actual cause of the
     *                               error.
     */
    @Override
    public StockQuote getQuote(String symbol) throws StockServiceException {
        //
        //why would you create an entire list to return only one?
        // if a list is all you can get - why loop all of them to get the first.
        // seems like you should just get the first one.  If the most recent is wanted, then order date, descending
        //puts the most recent record first.
        List<StockQuote> stockQuotes = null;
        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select * from quotes where symbol = '" + symbol + "' order by time desc limit 1";

            ResultSet resultSet = statement.executeQuery(queryString);

            stockQuotes = new ArrayList<>(resultSet.getFetchSize());
            while (resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                Date time = resultSet.getDate("time");
                BigDecimal price = resultSet.getBigDecimal("price");
                stockQuotes.add(new StockQuote(price, time, symbolValue));
                break;
            }
            //clean up the environment
            resultSet.close();
            statement.close();
            connection.close();
        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        if (stockQuotes.isEmpty()) {
            throw new StockServiceException("There is no stock data for:" + symbol);
        }
        return stockQuotes.get(0);
    }

    /**
     * Get a historical list of stock quotes for the provide symbol
     *
     * @param symbol the stock symbol to search for
     * @param from   the date of the first stock quote
     * @param until  the date of the last stock quote
     * @return a list of StockQuote instances
     * @throws StockServiceException if using the service generates an exception.
     *                               If this happens, trying the service may work, depending on the actual cause of the
     *                               error.
     */
    @Override
    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until) throws StockServiceException {
        List<StockQuote> stockQuotes = null;
        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(StockData.dateFormat);

            String fromDateString = simpleDateFormat.format(from.getTime());
            String toDateString = simpleDateFormat.format(until.getTime());

            String queryString = "select * from quotes where symbol = '" + symbol + "'"
                    + "and time BETWEEN '" + fromDateString + "' and '" + toDateString + "'";

            ResultSet resultSet = statement.executeQuery(queryString);
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());
            while (resultSet.next()) {
                String symbolValue = resultSet.getString("symbol");
                Date time = resultSet.getDate("time");
                BigDecimal price = resultSet.getBigDecimal("price");
                stockQuotes.add(new StockQuote(price, time, symbolValue));
            }
        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        if (stockQuotes.isEmpty()) {
            throw new StockServiceException("There is no stock data in this date range for:" + symbol);
        }
        return stockQuotes;
    }

    /**
     * @param symbol   the stock symbol to search for
     * @param from     the date of the first stock quote
     * @param until    the date of the last stock quote
     * @param interval ­ the number of StockQuotes to get. E.g. if Interval.DAY was
     *                 specified, then one StockQuote per day will be returned.
     *                 If HOURLY, then 24 hours in a day mean that 24 stock Quotes per day are returned.
     * @return
     */
    @Override
    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, Interval interval) throws StockServiceException {

        List<StockQuote> stockQuotes = null;
        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(StockData.dateFormat);

            String fromDateString = simpleDateFormat.format(from.getTime());
            String toDateString = simpleDateFormat.format(until.getTime());

            nextStockStartDate = (Calendar) from.clone();
            java.util.Date jdStartDate = nextStockStartDate.getTime();

            Calendar cStopDate = (Calendar) from.clone();
            cStopDate.add(Calendar.MINUTE, (interval.getMinutesToAdd()));
            java.util.Date jdStopDate = nextStockStartDate.getTime();

            String queryString = "select * from quotes where symbol = '" + symbol + "'"
                    + "and time BETWEEN '" + fromDateString + "' and '" + toDateString + "'";

            ResultSet resultSet = statement.executeQuery(queryString);
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());

            while (resultSet.next()) {
//convert result set date to Calendar to get time, then to java.util.Date for comparison
                Calendar resultSetDate = Calendar.getInstance();
                Timestamp timeStamp = resultSet.getTimestamp("time");
                resultSetDate.setTimeInMillis(timeStamp.getTime());
                java.util.Date time = resultSetDate.getTime();

                if (time.after(jdStartDate)) {
                    String symbolValue = resultSet.getString("symbol");
                    BigDecimal price = resultSet.getBigDecimal("price");
                    stockQuotes.add(new StockQuote(price, time, symbolValue));
// once one has been found to be added to our list,
// add the interval period (in minutes to both the start and stop comparison dates)
                    nextStockStartDate.add(Calendar.MINUTE, (interval.getMinutesToAdd()));
                    jdStartDate = nextStockStartDate.getTime();
                    nextStockStartDate.add(Calendar.MINUTE, (interval.getMinutesToAdd()));
                    jdStopDate = nextStockStartDate.getTime();
                }
            }

        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        if (stockQuotes.isEmpty()) {
            throw new StockServiceException("There is no stock data in this date range for:" + symbol);
        }
        return stockQuotes;
    }

}
