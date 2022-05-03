package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.commands.*;
import server.utility.CollectionFileManager;
import server.utility.CollectionManager;
import server.utility.CommandManager;
import server.utility.RequestHandler;

/**
 * Main server class. Creates all server instances.
 *
 * @author Huynh Tan Cuong.
 */
public class App {
    public static final int PORT = 25566;
    public static final int CONNECTION_TIMEOUT = 60 * 1000;
    public static final String ENV_VARIABLE = "HEHE";
    public static final Logger logger = LoggerFactory.getLogger("ServerLogger");

    public static void main(String[] args) {
        CollectionFileManager collectionFileManager = new CollectionFileManager(ENV_VARIABLE);
        CollectionManager collectionManager = new CollectionManager(collectionFileManager);
        CommandManager commandManager = new CommandManager(
                new AddCommand(collectionManager),
                new AddIfMaxCommand(collectionManager),
                new ClearCommand(collectionManager),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new FilterByPriceCommand(collectionManager),
                new HelpCommand(),
                new HistoryCommand(),
                new InfoCommand(collectionManager),
                new MinByPriceCommand(collectionManager),
                new RemoveByIdCommand(collectionManager),
                new RemoveGreaterCommand(collectionManager),
                new RemoveLowerCommand(collectionManager),
                new SaveCommand(collectionManager),
                new ServerExitCommand(),
                new ShowCommand(collectionManager),
                new UpdateCommand(collectionManager)
        );
        RequestHandler requestHandler = new RequestHandler(commandManager);
        Server server = new Server(PORT, CONNECTION_TIMEOUT, requestHandler);
        server.run();
    }
}
