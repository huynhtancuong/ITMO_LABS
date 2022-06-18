package server.commands;

import common.data.SpaceMarine;
import common.exceptions.*;
import common.interaction.User;
import server.utility.CollectionManager;
import server.utility.DatabaseCollectionManager;
import server.utility.DatabaseUserManager;
import server.utility.ResponseOutputer;

/**
 * Command 'remove_by_id'. Removes the element by its ID.
 */
public class RemoveByIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager, DatabaseUserManager databaseUserManager) {
        super("remove_by_id", "<ID>", "remove item from collection by ID");
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
            if (stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            long id = Long.parseLong(stringArgument);
            SpaceMarine marineToRemove = collectionManager.getById(id);
            if (marineToRemove == null) throw new MarineNotFoundException();
            if (!marineToRemove.getOwner().equals(user)) throw new PermissionDeniedException();
            if (!databaseCollectionManager.checkMarineUserId(marineToRemove.getId(), user)) throw new ManualDatabaseEditException();
            databaseCollectionManager.deleteMarineById(id);
            collectionManager.removeFromCollection(marineToRemove);
            ResponseOutputer.appendln("Soldier successfully removed!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.appenderror("Collection is empty!");
        } catch (NumberFormatException exception) {
            ResponseOutputer.appenderror("ID must be represented by a number!");
        } catch (MarineNotFoundException exception) {
            ResponseOutputer.appenderror("There is no soldier with this ID in the collection!");
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
