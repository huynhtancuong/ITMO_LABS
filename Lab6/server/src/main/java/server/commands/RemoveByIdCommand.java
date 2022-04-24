package server.commands;

import common.data.Ticket;
import common.exceptions.CollectionIsEmptyException;
import common.exceptions.TicketNotFoundException;
import common.exceptions.WrongAmountOfElementsException;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

/**
 * Command 'remove_by_id'. Removes the element by its ID.
 */
public class RemoveByIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    /**
     * Constructor
     * @param collectionManager instance of Collection Manager
     */
    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id <ID>",  "","remove item from collection by ID");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (stringArgument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long id = Long.parseLong(stringArgument);
            Ticket ticketToRemove = collectionManager.getById(id);
            if (ticketToRemove == null) throw new TicketNotFoundException();
            collectionManager.removeFromCollection(ticketToRemove);
            ResponseOutputer.appendln("Deleted ticket successfully");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.appenderror("Collection is empty");
        } catch (NumberFormatException exception) {
            ResponseOutputer.appenderror("ID must be integer number");
        } catch (TicketNotFoundException exception) {
            ResponseOutputer.appenderror("Can not find ticket with this ID");
        }
        return false;
    }
}
