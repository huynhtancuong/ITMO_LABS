package itmo.Commands;

import itmo.Data.Ticket;
import itmo.Exceptions.CollectionIsEmptyException;
import itmo.Exceptions.IncorrectInputInScriptException;
import itmo.Exceptions.TicketNotFoundException;
import itmo.Exceptions.WrongAmountOfElementsException;
import itmo.Utility.CollectionManager;
import itmo.Utility.Console;
import itmo.Utility.TicketAsker;

/**
 * Command 'remove_lower'. Removes elements lower than user entered.
 */
public class RemoveLowerCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private TicketAsker ticketAsker;

    /**
     * @param collectionManager Instance of Collection Manager
     * @param ticketAsker Instance of TicketAkser
     */
    public RemoveLowerCommand(CollectionManager collectionManager, TicketAsker ticketAsker) {
        super("remove_lower {element}", "remove all items from the collection that are less than the given one");

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
            collectionManager.removeLower(ticketToFind);
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
