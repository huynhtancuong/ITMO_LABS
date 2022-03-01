package itmo.Console;

import java.io.Console;

public class Main {

    public static void main(String[] args) {
	    Console console = System.console();

	    if (console == null) {
	        System.out.println("Console is not available.");
	        return;
        }

	    String username = console.readLine("Enter your username: ");
	    char[] password_arr = console.readPassword("Enter your password: ");
	    String password = String.valueOf(password_arr);
	    System.out.println("Your username is " + username);
	    System.out.println("Your password is " + password.toString());

    }
}
