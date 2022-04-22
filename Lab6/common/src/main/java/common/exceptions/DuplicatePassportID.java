package common.exceptions;

/**
 * It throws an exception if a passport ID is already in use.
 */
public class DuplicatePassportID extends Exception {
    /**
     * Constructor
     */
    public DuplicatePassportID() {
        super();
    }

    /**
     * Constructor
     * @param message Message
     */
    public DuplicatePassportID(String message) {
        super(message);
    }
}
