package edu.kathieRoy.advancedjava;

/**
 * this is a generic Class, that can be used to
 * return two arbitrary types from the method call
 *
 * @param <T>
 */
public class ArbType<T> {
    private T inputStr;

    public ArbType(T input) {
        this.inputStr = input;
    }

    public void setInputStr(T inputStr) {
        this.inputStr = inputStr;
    }

    public T getInputStr() {
        return inputStr;
    }
}