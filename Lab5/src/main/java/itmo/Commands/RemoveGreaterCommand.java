package itmo.Commands;

import itmo.Data.Ticket;
import itmo.Exceptions.CollectionIsEmptyException;
import itmo.Exceptions.IncorrectInputInScriptException;
import itmo.Exceptions.WrongAmountOfElementsException;
import itmo.Utility.CollectionManager;
import itmo.Utility.Console;
import itmo.Utility.TicketAsker;

/**
 * Command 'remove_greater'. Removes elements greater than user entered.
 */
public class RemoveGreaterCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private TicketAsker ticketAsker;

    /**
     * @param collectionManager Instance of Collection Manager
     * @param ticketAsker Instance of TicketAkser
     */
    public RemoveGreaterCommand(CollectionManager collectionManager, TicketAsker ticketAsker) {
        super("remove_greater {element}", "remove all items from the collection that are greater than the given one");
        this.collectionManager = collectionManager;
        this.ticketAsker = ticketAsker;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Ticket ticketToFind = new Ticket(
                collectionManager.generateNextId(),
                ticketAsker.askName(),
                ticketAsker.askCoordinates(),
                ticketAsker.askPrice(),
                ticketAsker.askTicketType(),
                ticketAsker.askPerson(false)
            );
            collectionManager.removeGreater(ticketToFind);
            Console.println("Removed item successfully");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("Collection is empty");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
