package itmo.Commands;

import itmo.Data.Ticket;
import itmo.Exceptions.CollectionIsEmptyException;
import itmo.Exceptions.TicketNotFoundException;
import itmo.Exceptions.WrongAmountOfElementsException;
import itmo.Utility.CollectionManager;
import itmo.Utility.Console;

/**
 * Command 'remove_by_id'. Removes the element by its ID.
 */
public class RemoveByIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id <ID>", "remove item from collection by ID");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long id = Long.parseLong(argument);
            Ticket ticketToRemove = collectionManager.getById(id);
            if (ticketToRemove == null) throw new TicketNotFoundException();
            collectionManager.removeFromCollection(ticketToRemove);
            Console.println("Deleted ticket successfully");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("Collection is empty");
        } catch (NumberFormatException exception) {
            Console.printError("ID must be number");
        } catch (TicketNotFoundException exception) {
            Console.printError("Can not find ticket with this ID");
        }
        return false;
    }
}
