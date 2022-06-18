package server.commands;

import common.exceptions.DatabaseHandlingException;
import common.exceptions.UserIsNotFoundException;
import common.exceptions.WrongAmountOfElementsException;
import common.interaction.MarineRaw;
import common.interaction.User;
import server.utility.CollectionManager;
import server.utility.DatabaseCollectionManager;
import server.utility.DatabaseUserManager;
import server.utility.ResponseOutputer;

/**
 * Command 'add'. Adds a new element to collection.
 */
public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public AddCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager, DatabaseUserManager databaseUserManager) {
        super("add", "{element}", "add a new element to the collection");
        this.collectionManager = collectionManager;
        this.databaseCollectionManager = databaseCollectionManager;
        this.databaseUserManager = databaseUserManager;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument, User user) {
        try {
            if (!databaseUserManager.checkUserByUsernameAndPassword(user)) throw new UserIsNotFoundException();
            if (!stringArgument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException();
            MarineRaw marineRaw = (MarineRaw) objectArgument;
            collectionManager.addToCollection(databaseCollectionManager.insertMarine(marineRaw, user));
            ResponseOutputer.appendln("Soldier successfully added!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (ClassCastException exception) {
            ResponseOutputer.appenderror("The object passed by the client is invalid!");
        } catch (DatabaseHandlingException exception) {
            ResponseOutputer.appenderror("An error occurred while accessing the database!");
        } catch (UserIsNotFoundException e) {
            ResponseOutputer.appenderror("Incorrect username or password!");
        }
        return false;
    }
}
