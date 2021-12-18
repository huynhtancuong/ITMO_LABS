package Exceptions;

public class WrongInstanceException extends Exception {
    public WrongInstanceException() {
        super();
    }
    public WrongInstanceException(String message) {
        super(message);
    }
}
