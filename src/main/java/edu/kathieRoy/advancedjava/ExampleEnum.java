package edu.kathieRoy.advancedjava;

public enum ExampleEnum {

    ALL_GOOD("excellent", 0),
    OK("This eror is not so bad", -1),
    PRETTY_BAD("Ok things are aren't good at all", -2),
    END_OF_WORLD("All done", -3);

    private String message;
    private int errorCode;

    ExampleEnum(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public int getErrorCode() {

        return errorCode;
    }

    public String getMessage() {

        return message;
    }
}



