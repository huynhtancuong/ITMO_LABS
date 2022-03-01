package itmo.Data;

public class Coordinates {
    private long x;
    private long y;

    public String getCSVString(String CSV_SEPARATOR) {
        String CSV_String = Long.toString(x) + CSV_SEPARATOR + Long.toString(y);
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
}
