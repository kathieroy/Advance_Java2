package edu.kathieRoy.advancedjava;

/**
 * this is a generic Class, that can be used to
 * return two arbitrary types from the method call
 *
 * @param <T>
 */
public class ArbType<T> {
    private final T inputStr;

    /**
     * @param input
     */
    public ArbType(T input) {
        this.inputStr = input;
    }


    /**
     *
     * @return
     */
    public T getInputStr() {
        return inputStr;
    }
}