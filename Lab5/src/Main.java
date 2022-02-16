import java.io.Console;

public class Main {

    public static void main(String[] args) {
	    Console console = System.console();

	    if (console == null) {
	        System.out.println("Console is not available.");
	        return;
        }

	    String userName = console.readLine("Enter your username: ");
	    System.out.println("Your username is " + userName);

    }
}
