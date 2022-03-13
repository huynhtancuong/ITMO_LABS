package itmo.Utility;

import itmo.run.App;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console {
    private CommandManager commandManager;
    private Scanner userScanner;
    private TicketAsker ticketAsker;
    private List<String> scriptStack = new ArrayList<>();

    public Console(CommandManager commandManager, Scanner scanner, TicketAsker ticketAsker) {
        this.commandManager = commandManager;
        this.userScanner = scanner;
        this.ticketAsker = ticketAsker;
    }

    public void interactiveMode() {
        String[] userCommand = {"", ""};
        int commandStatus;
        try {
            do {
                Console.print(App.PS1);
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandManager.addToHistory(userCommand[0]);
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        }
        catch (NoSuchElementException exception) {
            Console.printError("User input not found");
        }
        catch (IllegalStateException exception) {
            Console.printError("Unexcepted error");
        }
    }

    public void scriptMode() {

    }
    public int launchCommand(String[] userCommand) {
        return 0;
    }
    public static void print(Object toOut) {
        System.out.print(toOut);
    }
    public static void println(Object toOut) {
        System.out.println(toOut);
    }
    public static void printError(Object toOut) {
        System.out.println("Error: " +  toOut);
    }
    public static void printTable(Object toOut1, Object toOut2) {
        System.out.printf("%-37s%-1s%n", toOut1, toOut2);
    }

    @Override
    public String toString() {
        return "Console (class for launch command";
    }
}
