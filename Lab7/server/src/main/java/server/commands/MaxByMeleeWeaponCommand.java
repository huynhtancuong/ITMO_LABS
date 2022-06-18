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
 * Command 'max_by_melee_weapon'. Prints the element of the collection with maximum melee weapon.
 */
public class MaxByMeleeWeaponCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public MaxByMeleeWeaponCommand(CollectionManager collectionManager, DatabaseUserManager databaseUserManager) {
        super("max_by_melee_weapon", "", "display the element whose meleeWeapon field value is maximum");
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
            ResponseOutputer.appendln(collectionManager.maxByMeleeWeapon());
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
        return true;
    }
}
