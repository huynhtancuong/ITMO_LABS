package server;

import common.exceptions.NotInDeclaredLimitsException;
import common.exceptions.WrongAmountOfElementsException;
import common.utility.Outputer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.commands.*;
import server.utility.*;

/**
 * Main server class. Creates all server instances.
 *
 * @author Huynh Tan Cuong.
 */
public class App {
    // TODO: Add a where query to delete + 1 more condition so that the user is checked during change commands (you can change it in the database itself)
    private static final int MAX_CLIENTS = 1000;
    public static Logger logger = LogManager.getLogger("ServerLogger");
    private static String databaseUsername = "s336231";
    private static int port;
    private static String databaseHost;
    private static String databasePassword;
    private static String databaseAddress;

    public static void main(String[] args) {
        if (!initialize(args)) return;
        DatabaseHandler databaseHandler = new DatabaseHandler(databaseAddress, databaseUsername, databasePassword);
        DatabaseUserManager databaseUserManager = new DatabaseUserManager(databaseHandler);
        DatabaseCollectionManager databaseCollectionManager = new DatabaseCollectionManager(databaseHandler, databaseUserManager);
        CollectionManager collectionManager = new CollectionManager(databaseCollectionManager);
        CommandManager commandManager = new CommandManager(
                new HelpCommand(),
                new InfoCommand(collectionManager, databaseUserManager),
                new ShowCommand(collectionManager, databaseUserManager),
                new AddCommand(collectionManager, databaseCollectionManager, databaseUserManager),
                new UpdateCommand(collectionManager, databaseCollectionManager, databaseUserManager),
                new RemoveByIdCommand(collectionManager, databaseCollectionManager, databaseUserManager),
                new ClearCommand(collectionManager, databaseCollectionManager, databaseUserManager),
                new ExitCommand(databaseUserManager),
                new ExecuteScriptCommand(databaseUserManager),
                new AddIfMinCommand(collectionManager, databaseCollectionManager, databaseUserManager),
                new RemoveGreaterCommand(collectionManager, databaseCollectionManager, databaseUserManager),
                new HistoryCommand(databaseUserManager),
                new SumOfHealthCommand(collectionManager, databaseUserManager),
                new MaxByMeleeWeaponCommand(collectionManager, databaseUserManager),
                new FilterByWeaponTypeCommand(collectionManager, databaseUserManager),
                new ServerExitCommand(databaseUserManager),
                new LoginCommand(databaseUserManager),
                new RegisterCommand(databaseUserManager)
        );
        Server server = new Server(port, MAX_CLIENTS, commandManager);
        server.run();
        databaseHandler.closeConnection();
    }

    /**
     * Controls initialization.
     */
    private static boolean initialize(String[] args) {
        try {
            if (args.length != 3) throw new WrongAmountOfElementsException();
            port = Integer.parseInt(args[0]);
            if (port < 0) throw new NotInDeclaredLimitsException();
            databaseHost = args[1];
            databasePassword = args[2];
            databaseAddress = "jdbc:postgresql://" + databaseHost + ":5432/studs";
            return true;
        } catch (WrongAmountOfElementsException exception) {
            String jarName = new java.io.File(App.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath())
                    .getName();
            Outputer.println("Usage: 'java -jar " + jarName + " <port> <db_host> <db_password>'");
        } catch (NumberFormatException exception) {
            Outputer.printerror("The port must be represented by a number!");
            App.logger.fatal("The port must be represented by a number!");
        } catch (NotInDeclaredLimitsException exception) {
            Outputer.printerror("Port cannot be negative!");
            App.logger.fatal("Port cannot be negative!");
        }
        App.logger.fatal("Launch port initialization error!");
        return false;
    }
}
