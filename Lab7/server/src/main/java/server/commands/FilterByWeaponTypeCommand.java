package server.commands;

import common.data.Weapon;
import common.exceptions.CollectionIsEmptyException;
import common.exceptions.DatabaseHandlingException;
import common.exceptions.UserIsNotFoundException;
import common.exceptions.WrongAmountOfElementsException;
import common.interaction.User;
import server.utility.CollectionManager;
import server.utility.DatabaseUserManager;
import server.utility.ResponseOutputer;

/**
 * Command 'filter_by_weapon_type'. Filters the collection by weapon type.
 */
public class FilterByWeaponTypeCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public FilterByWeaponTypeCommand(CollectionManager collectionManager, DatabaseUserManager databaseUserManager) {
        super("filter_by_weapon_type", "<weaponType>",
                "display elements whose weaponType field value is equal to the given one");
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
            if (stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Weapon weapon = Weapon.valueOf(stringArgument.toUpperCase());
            String filteredInfo = collectionManager.weaponFilteredInfo(weapon);
            if (!filteredInfo.isEmpty()) ResponseOutputer.appendln(filteredInfo);
            else ResponseOutputer.appendln("There are no soldiers in the collection with the selected weapon type!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.appenderror("Collection is empty!");
        } catch (IllegalArgumentException exception) {
            ResponseOutputer.appenderror("Weapon not listed!");
            ResponseOutputer.appendln("List of ranged weapons - " + Weapon.nameList());
        } catch (UserIsNotFoundException e) {
            ResponseOutputer.appenderror("Incorrect username or password!");
        } catch (DatabaseHandlingException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
