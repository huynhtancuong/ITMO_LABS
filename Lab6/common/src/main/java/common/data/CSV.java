package common.data;

/**
 * Defining a new interface called `CSV` that has one method called `getCSVString`.
 */
public interface CSV {
    /**
     * Given a string, return a string that is a CSV representation of the string
     *
     * @param string The string to be converted to CSV format.
     * @return The string is being returned.
     */
    String getCSVString(String string);
}
