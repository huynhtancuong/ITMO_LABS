package server.commands;

import common.exceptions.CollectionIsEmptyException;
import common.exceptions.DatabaseHandlingException;
import common.exceptions.UserIsNotFoundException;
import common.exceptions.WrongAmountOfElementsException;
import common.interaction.User;
import server.utility.CollectionManager;
import server.utility.DatabaseUserManager;
import server.utility.ResponseOutputer;

/**
 * Command 'sum_of_health'. Prints the sum of health of all marines.
 */
public class SumOfHealthCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public SumOfHealthCommand(CollectionManager collectionManager, DatabaseUserManager databaseUserManager) {
        super("sum_of_health", "", "display the sum of the health field values for all elements of the collection");
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
            double sum_of_health = collectionManager.getSumOfHealth();
            if (sum_of_health == 0) throw new CollectionIsEmptyException();
            ResponseOutputer.appendln("Total health of all soldiers: " + sum_of_health);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.appenderror("Collection is empty!");
        } catch (UserIsNotFoundException e) {
            ResponseOutputer.appenderror("Incorrect username or password!");
        } catch (DatabaseHandlingException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
