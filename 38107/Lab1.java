
import static java.lang.Math.*;


public class Lab1 {
    public static void main(String[] args) {
        /**
            BEGIN OF SECTION 1
        */
            int[] a = new int[(17-3)/2+1];
            int counter = 0;
            for (int i = 3; i<=17; i+=2)
            {
                if (i%2==1) 
                {
                    a[counter]=i; 
                    counter++;
                }

            }
            for (int i = 0; i<counter; i++)
            {
                //System.out.println(a[i]);
            }
        /**
         * END OF SECTION 1
         */

        /**
         * BEGIN OF SECTION 2
         */
            double[] x = new double[13];
            for (int i = 0; i < 13; i++) 
            {
                x[i] = random()*20-12;
            }
            for (int i = 0; i<counter; i++)
            {
                //System.out.printf("%.4f\n", x[i]);
            }
        /**
         * END OF SECTION 2
         */

        /**
         * BEGIN OF SECTION 3
         */
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
                    System.out.printf("%.4f\t", g[i][j]);
                }
                System.out.println();
            }
        /**
         * END OF SECTION 3
         */
    }
}