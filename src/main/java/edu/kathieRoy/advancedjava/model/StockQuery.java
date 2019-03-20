package edu.kathieRoy.advancedjava.model;

import org.apache.http.annotation.Immutable;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Calendar;

/**
 * This class is used to a single query to stock service.
 */
@Immutable
public class StockQuery extends StockData {

    private String symbol;
    private Calendar from;
    private Calendar until;
    private String sInterval;
    private Interval interval;


    public StockQuery(@NotNull String symbol, @NotNull String from, @NotNull String until, Interval interval) throws ParseException {
        super();
        this.symbol = symbol;
        this.from = Calendar.getInstance();
        this.until = Calendar.getInstance();
        System.out.println(simpleDateFormat);
        this.from.setTime(simpleDateFormat.parse(from));
        this.until.setTime(simpleDateFormat.parse(until));
        this.interval = interval;
    }

    /**
     * @return get the stock symbol associated with this query
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @return get the start Calendar associated with this query
     */
    public Calendar getFrom() {
        return from;
    }

    /**
     * @return get the end Calendar associated with this query
     */
    public Calendar getUntil() {
        return until;
    }
}
