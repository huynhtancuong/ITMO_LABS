package server.commands;

import common.data.SpaceMarine;
import common.exceptions.*;
import common.interaction.User;
import server.utility.CollectionManager;
import server.utility.DatabaseCollectionManager;
import server.utility.DatabaseUserManager;
import server.utility.ResponseOutputer;

/**
 * Command 'clear'. Clears the collection.
 */
public class ClearCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public ClearCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager, DatabaseUserManager databaseUserManager) {
        super("clear", "", "clear the collection");
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
            if (!stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            for (SpaceMarine marine : collectionManager.getCollection()) {
                if (!marine.getOwner().equals(user)) throw new PermissionDeniedException();
                if (!databaseCollectionManager.checkMarineUserId(marine.getId(), user)) throw new ManualDatabaseEditException();
            }
            databaseCollectionManager.clearCollection();
            collectionManager.clearCollection();
            ResponseOutputer.appendln("Collection cleared!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (DatabaseHandlingException exception) {
            ResponseOutputer.appenderror("An error occurred while accessing the database!");
        } catch (PermissionDeniedException exception) {
            ResponseOutputer.appenderror("Insufficient rights to execute this command!");
            ResponseOutputer.appendln("Objects owned by other users are read-only.");
        } catch (ManualDatabaseEditException exception) {
            ResponseOutputer.appenderror("A direct database change has occurred!");
            ResponseOutputer.appendln("Restart the client to avoid possible errors.");
        } catch (UserIsNotFoundException e) {
            ResponseOutputer.appenderror("Incorrect username or password!");
        }
        return false;
    }
}
