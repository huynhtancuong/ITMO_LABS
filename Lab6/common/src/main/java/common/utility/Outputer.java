package common.utility;

/**
 * Class for outputting something
 */
public class Outputer {
    /**
     * Prints toOut.toString() to Outputer
     *
     * @param toOut Object to print
     */
    public static void print(Object toOut) {
        System.out.print(toOut);
    }

    /**
     * Prints \n to Outputer
     */
    public static void println() {
        System.out.println();
    }

    /**
     * Prints toOut.toString() + \n to Outputer
     *
     * @param toOut Object to print
     */
    public static void println(Object toOut) {
        System.out.println(toOut);
    }

    /**
     * Prints error: toOut.toString() to Outputer
     *
     * @param toOut Error to print
     */
    public static void printerror(Object toOut) {
        System.out.println("Error: " + toOut);
    }

    /**
     * Prints formatted 2-element table to Outputer
     *
     * @param element1 Left element of the row.
     * @param element2 Right element of the row.
     */
    public static void printtable(Object element1, Object element2) {
        System.out.printf("%-37s%-1s%n", element1, element2);
    }
}
