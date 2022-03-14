package itmo.Data;

public class Coordinates implements CSV {
    private long x;
    private Long y = Long.valueOf(0);

    public String getCSVString(String CSV_SEPARATOR) {
        String CSV_String = x + CSV_SEPARATOR + y;
        return CSV_String;
    }
    public Coordinates(){}
    public Coordinates(long x, long y) {
        this.x = x;
        this.y = y;
    }
    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    @Override
    public int hashCode() {
        return y.hashCode() + (int) x;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinates) {
            Coordinates coordinatesObj = (Coordinates) obj;
            return (x == coordinatesObj.getX()) && y.equals(coordinatesObj.getY());
        }
        return false;
    }

    @Override
    public String toString() {
        return "\n-X = " + getX() + "\n-Y = " + getY();
    }

}
