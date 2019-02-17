package edu.kathieRoy.advancedjava.model;

/**
 * This enum will hold the possible Time Intervals
 * to be used i retrieving stock prices
 */
public enum Interval {

    QUARTER_HOUR("Every quarter hour", 96),
    HALF_HOUR("Every half hour", 48),
    HOUR("Every Hour", 24),
    DAY("Once a day", 1);

    private String message;
    private int numberPerDay;

    Interval(String message, int numPerDay) {
        this.message = message;
        this.numberPerDay = numPerDay;
    }

    public int getNumberPerDay() {
        return numberPerDay;
    }
}
