package edu.kathieRoy.advancedjava.model;


import javax.persistence.*;

/**
 * Models the StockInterests table
 */
@Entity
public class StockInterests {

    private int id;
    private Person person;
    private String symbol;

    /**
     * Create a StockInterests that needs to be initialized
     */
    public StockInterests() {
        // this empty constructor is required by hibernate framework
    }

    /**
     * Create a valid StockInterests
     *
     * @param person the person to assign the stock symbol to
     * @param symbol  the symbol to associate the person with
     */
    public StockInterests(Person person, String symbol) {
        setSymbol(symbol);
        setPerson(person);
    }

    /**
     * Primary Key - Unique ID for a particular row in the stockInterests table.
     *
     * @return an integer value
     */
    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }
    /**
     * Set the unique ID for a particular row in the stockInterests table.
     * This method should not be called by client code. The value is managed in internally.
     *
     * @param id a unique value.
     */
    public void setId(int id) {
        this.id = id;
    }


    /*
     * @return - get the Person associated with this hobby
     */
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "ID", nullable = false)
    public Person getPerson() {
        return person;
    }
    /**
     * Specify the Person associated with the hobby.
     *
     * @param person a person instance
     */
    public void setPerson(Person person) {
        this.person = person;
    }


    @Basic
    @Column(name = "symbol", nullable = false, insertable = true, updatable = true, length = 256)
    public String getSymbol() {
        return symbol;
    }

    /**
     * Specify the stock's symbol
     *
     * @param symbol a String value
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockInterests stockInterests = (StockInterests) o;
        if (id != stockInterests.id) return false;
        if (person != stockInterests.person) return false;
        if (symbol != null ? !symbol.equals(stockInterests.symbol) : stockInterests.symbol != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + person.getId();
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StockInterests{" +
                "id=" + id +
                ", person_id ='" + person.getId() + '\'' +
                ", person_name ='" + person.getLastName() + '\'' +
                ", symbol='" + symbol +
                '}';
    }
}
