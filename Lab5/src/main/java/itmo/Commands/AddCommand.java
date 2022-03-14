package itmo.Commands;

import itmo.Exceptions.IncorrectInputInScriptException;
import itmo.Exceptions.WrongAmountOfElementsException;
import itmo.Utility.CollectionManager;
import itmo.Utility.Console;
import itmo.Utility.TicketAsker;
import itmo.Data.Ticket;


/**
 * Command 'add'. Adds a new element to collection.
 */
public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private TicketAsker ticketAsker;

    /**
     * Constructor
     * @param collectionManager Collection Manager Class which manage collection
     * @param ticketAsker Ticket Asker which responsible for asking input data
     */
    public AddCommand(CollectionManager collectionManager, TicketAsker ticketAsker) {
        super("add {element}", "add new item to collection");
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
            collectionManager.addToCollection(new Ticket(
                collectionManager.generateNextId(),
                ticketAsker.askName(),
                ticketAsker.askCoordinates(),
                ticketAsker.askPrice(),
                ticketAsker.askTicketType(),
                ticketAsker.askPerson(false)
            ));
            Console.println("Created new item successfully");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Usage: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
