package itmo.run;

import itmo.Commands.*;
import itmo.Exceptions.ExecuteScriptCommand;
import itmo.Utility.*;

import java.util.Scanner;

/**
 * Class which create all instance and run the program
 */
public class App {
    /**
     * Symbol for program
     */
    public static String PS1 = "$";
    /**
     * Another symbol for program
     */
    public static String PS2 = ">";

    /**
     * entry point for the program
     * @param args argument passed by JVM
     */
    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {
            final String envVariable = "HEHE";

            TicketAsker ticketAsker = new TicketAsker(userScanner);
            FileManager fileManager = new FileManager(envVariable);
            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager();

            commandManager.setHelpCommand(new HelpCommand());
            commandManager.setClearCommand(new ClearCommand(collectionManager));
            commandManager.setHelpCommand(new HelpCommand());
            commandManager.setExitCommand(new ExitCommand());
            commandManager.setAddIfMaxCommand(new AddIfMaxCommand(collectionManager, ticketAsker));
            commandManager.setShowCommand(new ShowCommand(collectionManager));
            commandManager.setSaveCommand(new SaveCommand(collectionManager));
            commandManager.setInfoCommand(new InfoCommand(collectionManager));
            commandManager.setUpdateCommand(new UpdateCommand(collectionManager, ticketAsker));
            commandManager.setRemoveByIdCommand(new RemoveByIdCommand(collectionManager));
            commandManager.setExecuteScriptCommand(new ExecuteScriptCommand());
            commandManager.setHistoryCommand(new HistoryCommand());
            commandManager.setRemoveGreaterCommand(new RemoveGreaterCommand(collectionManager, ticketAsker));
            commandManager.setRemoveLowerCommand(new RemoveLowerCommand(collectionManager, ticketAsker));
            commandManager.setRemoveByPersonCommand(new RemoveByPersonCommand(collectionManager, ticketAsker));
            commandManager.setMinByPriceCommand(new MinByPriceCommand(collectionManager));
            commandManager.setFilterByPriceCommand(new FilterByPriceCommand(collectionManager));

            Console console = new Console(commandManager, userScanner, ticketAsker);

            console.interactiveMode();
        }
    }
}
