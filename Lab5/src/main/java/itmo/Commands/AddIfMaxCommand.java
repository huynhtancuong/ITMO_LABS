package itmo.Commands;

import itmo.Data.Ticket;
import itmo.Exceptions.IncorrectInputInScriptException;
import itmo.Exceptions.WrongAmountOfElementsException;
import itmo.Utility.CollectionManager;
import itmo.Utility.Console;
import itmo.Utility.TicketAsker;

/**
 * Command 'add_if_max'. Adds a new element to collection if it's greater than the greatest one.
 */
public class AddIfMaxCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private TicketAsker ticketAsker;

    /**
     * @param collectionManager Collection Manager Class which manage collection
     * @param ticketAsker Ticket Asker which responsible for asking input data
     */
    public AddIfMaxCommand(CollectionManager collectionManager, TicketAsker ticketAsker) {
        super("add_if_max {element}", "add a new element if its value is less than that of the smallest");
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
            Ticket ticketToAdd = new Ticket(
                collectionManager.generateNextId(),
                ticketAsker.askName(),
                ticketAsker.askCoordinates(),
                ticketAsker.askPrice(),
                ticketAsker.askTicketType(),
                ticketAsker.askPerson(false)
            );

            if (collectionManager.collectionSize() == 0 || ticketToAdd.compareTo(collectionManager.getGreatestValue()) > 0) {
                collectionManager.addToCollection(ticketToAdd);
                Console.println("Item added successfully.");
                return true;
            } else Console.printError("The value of the item is lower than the value of the greatest of the items!");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Usage: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {

        }
        return false;
    }
}
