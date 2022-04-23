/**
 * This class is used to store the coordinates of a point
 */
package common.data;


import java.io.Serializable;

/**
 * This class is used to store the x and y coordinates of a point
 */
public class Coordinates implements CSV, Serializable {
    private long x;
    private Long y = Long.valueOf(0);

    /**
     * Constructor
     */
    public Coordinates(double x, Float y) {

    }


    /**
     * Given a CSV_SEPARATOR, return a CSV_String of the x and y coordinates
     *
     * @param CSV_SEPARATOR the character that separates the x and y values in the CSV string.
     * @return The string "1,2"
     */
    public String getCSVString(String CSV_SEPARATOR) {
        String CSV_String = x + CSV_SEPARATOR + y;
        return CSV_String;
    }

    /**
     * Constructor
     * @param x x coordinate
     * @param y y coordinate
     */
    public Coordinates(long x, long y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Returns the y coordinate of the point
     *
     * @return The value of the y field.
     */
    public long getY() {
        return y;
    }

    /**
     * It sets the y coordinate of the point.
     *
     * @param y The y coordinate of the point.
     */
    public void setY(long y) {
        this.y = y;
    }

    /**
     * Get the x coordinate of the point
     *
     * @return The value of the instance variable x.
     */
    public long getX() {
        return x;
    }

    /**
     * It sets the x value to the given value.
     *
     * @param x The x coordinate of the point.
     */
    public void setX(long x) {
        this.x = x;
    }

    @Override
    // Overriding the hashCode method of the Object class.
    public int hashCode() {
        return y.hashCode() + (int) x;
    }

    @Override
    // Overriding the equals method of the Object class.
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinates) {
            Coordinates coordinatesObj = (Coordinates) obj;
            return (x == coordinatesObj.getX()) && y.equals(coordinatesObj.getY());
        }
        return false;
    }

    @Override
    // Overriding the toString method of the Object class.
    public String toString() {
        return "\n-X = " + getX() + "\n-Y = " + getY();
    }

}
