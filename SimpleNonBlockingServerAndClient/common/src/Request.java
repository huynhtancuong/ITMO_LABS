import java.io.Serializable;

public class Request implements Serializable {
    public int number;
    public String string;
    public Request(int number, String string) {
        this.number = number;
        this.string = string;
    }

    @Override
    public String toString() {
        return "Request{" +
                "number=" + number +
                ", string='" + string + '\'' +
                '}';
    }
}
