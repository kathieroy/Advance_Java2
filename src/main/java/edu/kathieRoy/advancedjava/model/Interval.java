package edu.kathieRoy.advancedjava.model;

/**
 * This enum will hold the possible Time Intervals
 * to be used when retrieving stock prices,
 * it will also hold the string values that would corrospond to valid input for the
 * 4th String argument on the run command of BasicStockQuoteApplication
 */
public enum Interval {

    QUARTER_HOUR("Every quarter hour", 96, 15, "QUARTER_HOUR"),
    HALF_HOUR("Every half hour", 48, 30, "HALF_HOUR"),
    HOUR("Every Hour", 24, 60, "HOUR"),
    DAY("Once a day", 1, 1440, "DAY");

    private String message;
    private int numberPerDay;
    private int minutesToAdd;
    private String sInterval;

    Interval(String message, int numPerDay, int minutesToAdd, String sInterval) {
        this.message = message;
        this.numberPerDay = numPerDay;
        this.minutesToAdd = minutesToAdd;
        this.sInterval = sInterval;
    }

    public int getNumberPerDay() {
        return numberPerDay;
    }

    public int getMinutesToAdd() {
        return minutesToAdd;
    }

    public String getsInterval() {
        return sInterval;
    }
}
