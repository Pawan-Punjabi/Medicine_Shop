package exceptions;

public class NoQuantityLeftException extends Exception {
    public NoQuantityLeftException(String message) {
        super(message);
    }
}