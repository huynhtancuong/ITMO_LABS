import static java.lang.Math.*;
public class Lab1 {

    public static double myRandom() {
        double number = random()*(1+0.0001);
        while (number > 1) {
            number = random()*(1+0.0001);
        }
        return number;
    }

    public static void main(String[] args) {

            int[] a = new int[(17-3)/2+1]; //odd
            int counter = 0;
            for (int i = 3; i<=17; i+=2)
            {
                a[counter]=i; 
                counter++;

            }
            for (int i = 0; i<counter; i++)
            {
                //System.out.println(a[i]);
            }

            double[] x = new double[13];
            for (int i = 0; i < 13; i++) 
            {
                x[i] = myRandom()*20-12;
            }

            /**
             * random() - [0,1]
             * nextAfter(3) -> 3.000000001
             * 
             */
            for (int i = 0; i<counter; i++)
            {
                System.out.printf("%.4f\n", x[i]);
            }

            double[][] g = new double[8][13];
            for (int i = 0; i < g.length; i++)
            {
                for (int j = 0; j < g[i].length; j++)
                {
                    if (a[i]==15) 
                    {
                        g[i][j] = asin(cos(x[j]));
                    }
                    else if (a[i] == 3 || a[i] == 7 || a[i] == 9 || a[i] == 11)
                    {
                        g[i][j] = asin((1/3)*pow(((x[j]-2)/2)*E+1, 2));
                    }
                    else 
                    {
                        g[i][j] = 3/(tan(tan(pow(x[j], (x[j]-2)/3))));
                    }
                }
            }

            for (int i = 0; i < g.length; i++)
            {
                for (int j = 0; j < g[i].length; j++)
                {
                    System.out.printf("%10.4f", g[i][j]);
                }
                System.out.println();
            }

    }
}