import static java.lang.Math.*;


public class Lab1 {
    public static void main(String[] args) {
        /**
            BEGIN OF SECTION 1
        */
            long[] a = new long[(25-7)/2+1];
            int counter = 0;
            for (int i = 7; i<=25; i+=2)
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
            float[] x = new float[11];
            for (int i = 0; i < 11; i++) 
            {
                x[i] = (float)random()*4-2;
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
            double[][] g = new double[10][11];
            for (int i = 0; i < g.length; i++)
            {
                for (int j = 0; j < g[i].length; j++)
                {
                    if (a[i]==23) 
                    {
                        double A = (3/4 + atan(x[j]/4));
                        double B = pow(pow(x[j]/2, x[j])*A, 2);
                        double C = B-1;
                        double D = (1/4)*C;
                        double F = pow(pow((2+x[j])/x[j], x[j]), (atan(x[j]/4)/2/3));
                        g[i][j] = pow(D, F);
                    }
                    else if (a[i] == 7 || a[i] == 9 || a[i] == 17 || a[i] == 19 || a[i] == 21)
                    {
                        g[i][j] = asin(1/(pow(E, abs(2*pow(E, x[j])))));
                    }
                    else 
                    {
                        g[i][j] = cbrt(pow((1/2-log(abs(x[j])))/3, 3));
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