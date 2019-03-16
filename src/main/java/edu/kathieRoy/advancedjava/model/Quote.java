package edu.kathieRoy.advancedjava.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**

/**
 * Models the Quote table
 */
@Entity
@Table(name="quotes")
public class Quote extends StockData {
    private int id;
    private String symbol;
    private BigDecimal price;
    private Timestamp time;



    /**
     * Non argument 'default' constructor
     * required for ORM mapping framework like Hibernate
     */
    public Quote() {
    }

    /**
     * Create a quote
     * @param symbol
     * @param price
     * @param date
     */
    public Quote( String symbol, BigDecimal price, Timestamp date)  {
        setSymbol(symbol);
        setPrice(price);
        setTime(date);
    }

    /**
     * Primary Key - Unique ID for a particular row in the quote table.
     *
     * @return an integer value
     */

    @Column(name = "ID", nullable = false, insertable = true, updatable = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * Set the unique ID for a particular row in the quote table.
     * This method should not be called by client code. The value is managed in internally.
     *
     * @param id a unique value.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the stock quote's symbol
     */
    @Basic
    @Column(name = "symbol", nullable = false, insertable = true, updatable = true, length = 4)
    public String getSymbol() {
        return symbol;
    }

    /**
     * Specify the stock quotes' symbol
     * @param symbol a String value
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     *
     * @return the stock quote's date/time
     */
    @Basic
    @Column(name = "time", nullable = false, insertable = true, updatable = true, length = 25)
    public Timestamp getTime() { return time; }

    /**
     * Specify stock quotes' price
     * @param price a BigDecimal
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     *
     * @return the stock quote's price
     */
    @Basic
    @Column(name = "price", nullable = false, insertable = true, updatable = true, length = 15)
    public BigDecimal getPrice() { return price; }

    /**
     * Specify stock quotes' date/time
     * @param time a date
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        if (id != quote.id) return false;
        if (symbol != null ? !symbol.equals(quote.symbol) : quote.symbol != null)
            return false;
        if (time != null ? !time.equals(quote.time) : quote.time != null)
            return false;
        if (price != null ? !price.equals(quote.price) : quote.price != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String dateString = simpleDateFormat.format(time);
        return "StockQuote{" +
                "price=" + price +
                ", date=" + dateString +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
