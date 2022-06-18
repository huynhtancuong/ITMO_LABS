package server.commands;

import common.exceptions.DatabaseHandlingException;
import common.exceptions.UserIsNotFoundException;
import common.exceptions.WrongAmountOfElementsException;
import common.interaction.User;
import server.utility.DatabaseUserManager;
import server.utility.ResponseOutputer;

/**
 * Command 'history'. It's here just for logical structure.
 */
public class HistoryCommand extends AbstractCommand {

    public HistoryCommand(DatabaseUserManager databaseUserManager) {
        super("history", "", "display history of used commands");
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
