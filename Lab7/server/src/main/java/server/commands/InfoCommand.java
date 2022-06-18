package server.commands;

import common.exceptions.DatabaseHandlingException;
import common.exceptions.UserIsNotFoundException;
import common.exceptions.WrongAmountOfElementsException;
import common.interaction.User;
import server.utility.CollectionManager;
import server.utility.DatabaseUserManager;
import server.utility.ResponseOutputer;

import java.time.LocalDateTime;

/**
 * Command 'info'. Prints information about the collection.
 */
public class InfoCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager, DatabaseUserManager databaseUserManager) {
        super("info", "", "display information about the collection");
        this.collectionManager = collectionManager;
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
            LocalDateTime lastInitTime = collectionManager.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "initialization has not yet taken place in this session" :
                    lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

            ResponseOutputer.appendln("Collection details:");
            ResponseOutputer.appendln(" Type of: " + collectionManager.collectionType());
            ResponseOutputer.appendln(" Amount of elements: " + collectionManager.collectionSize());
            ResponseOutputer.appendln(" Date of last initialization: " + lastInitTimeString);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (UserIsNotFoundException e) {
            ResponseOutputer.appenderror("Incorrect username or password!");
        } catch (DatabaseHandlingException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
