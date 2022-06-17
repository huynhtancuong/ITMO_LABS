package server.commands;

import common.data.*;
import common.exceptions.*;
import common.interaction.MarineRaw;
import common.interaction.User;
import server.utility.CollectionManager;
import server.utility.DatabaseCollectionManager;
import server.utility.ResponseOutputer;

import java.time.LocalDateTime;

/**
 * Command 'update'. Updates the information about selected marine.
 */
public class UpdateCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public UpdateCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("update", "<ID> {element}", "update collection element value by ID");
        this.collectionManager = collectionManager;
        this.databaseCollectionManager = databaseCollectionManager;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument, User user) {
        try {
            if (stringArgument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            long id = Long.parseLong(stringArgument);
            if (id <= 0) throw new NumberFormatException();
            SpaceMarine oldMarine = collectionManager.getById(id);
            if (oldMarine == null) throw new MarineNotFoundException();
            if (!oldMarine.getOwner().equals(user)) throw new PermissionDeniedException();
            if (!databaseCollectionManager.checkMarineUserId(oldMarine.getId(), user)) throw new ManualDatabaseEditException();
            MarineRaw marineRaw = (MarineRaw) objectArgument;

            databaseCollectionManager.updateMarineById(id, marineRaw);

            String name = marineRaw.getName() == null ? oldMarine.getName() : marineRaw.getName();
            Coordinates coordinates = marineRaw.getCoordinates() == null ? oldMarine.getCoordinates() : marineRaw.getCoordinates();
            LocalDateTime creationDate = oldMarine.getCreationDate();
            double health = marineRaw.getHealth() == -1 ? oldMarine.getHealth() : marineRaw.getHealth();
            AstartesCategory category = marineRaw.getCategory() == null ? oldMarine.getCategory() : marineRaw.getCategory();
            Weapon weaponType = marineRaw.getWeaponType() == null ? oldMarine.getWeaponType() : marineRaw.getWeaponType();
            MeleeWeapon meleeWeapon = marineRaw.getMeleeWeapon() == null ? oldMarine.getMeleeWeapon() : marineRaw.getMeleeWeapon();
            Chapter chapter = marineRaw.getChapter() == null ? oldMarine.getChapter() : marineRaw.getChapter();

            collectionManager.removeFromCollection(oldMarine);
            collectionManager.addToCollection(new SpaceMarine(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    health,
                    category,
                    weaponType,
                    meleeWeapon,
                    chapter,
                    user
            ));
            ResponseOutputer.appendln("Soldier successfully changed!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + " " + getUsage() + "'");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.appenderror("Collection is empty!");
        } catch (NumberFormatException exception) {
            ResponseOutputer.appenderror("ID must be represented as a positive number!");
        } catch (MarineNotFoundException exception) {
            ResponseOutputer.appenderror("There is no soldier with this ID in the collection!");
        } catch (ClassCastException exception) {
            ResponseOutputer.appenderror("The object passed by the client is invalid!");
        } catch (DatabaseHandlingException exception) {
            ResponseOutputer.appenderror("An error occurred while accessing the database!");
        } catch (PermissionDeniedException exception) {
            ResponseOutputer.appenderror("Insufficient rights to execute this command!");
            ResponseOutputer.appendln("Objects owned by other users are read-only.");
        } catch (ManualDatabaseEditException exception) {
            ResponseOutputer.appenderror("A direct database change has occurred!");
            ResponseOutputer.appendln("Restart the client to avoid possible errors.");
        }
        return false;
    }
}
