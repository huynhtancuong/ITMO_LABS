import static java.lang.Math.*;

public class test {

    public static double myRandom() {
        double number = random()*(1+1);
        while (number > 1) {
            number = random()*(1+1);
        }
        return number;
    }
    public static void main(String[] args)
    {
        double i = 0;
        while (i<8)
        {
            i = myRandom()*20-12;
            System.out.printf("%f\n", i);
        }
    }
}
