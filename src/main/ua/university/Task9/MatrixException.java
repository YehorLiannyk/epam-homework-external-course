package main.ua.university.Task9;

public class MatrixException extends RuntimeException {
    public static final String ERROR_MSG = "Can't perform operations with matrix of incompatible sizes";
    public static final String ERROR_MSG_BECAUSE = "Can't perform operations with matrix of incompatible sizes: ";

    public MatrixException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public MatrixException(Throwable err) {
        this(ERROR_MSG, err);
    }

    public MatrixException() {
        this(ERROR_MSG, new Throwable(""));
    }

    public MatrixException(String errorMessage) {
        this(ERROR_MSG_BECAUSE + errorMessage, new Throwable(""));
    }
}
