package main.ua.advanced.practice8;

public class DBDataException extends RuntimeException {
    private static final String DEFAULT_MSG = "Data was damaged or database have two equal objects with different ids";

    public DBDataException(String msg) {
        super(msg);
    }

    public DBDataException() {
        this(DEFAULT_MSG);
    }
}
