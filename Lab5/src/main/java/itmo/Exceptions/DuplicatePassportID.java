package itmo.Exceptions;

public class DuplicatePassportID extends Exception {
    public DuplicatePassportID() {
        super();
    }
    public DuplicatePassportID(String message) {
        super(message);
    }
}
