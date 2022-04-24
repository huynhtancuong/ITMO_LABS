package server.commands;

import common.data.Ticket;
import common.exceptions.WrongAmountOfElementsException;
import common.interaction.TicketRaw;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;


/**
 * Command 'add'. Adds a new element to collection.
 */
public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    /**
     * Constructor
     * @param collectionManager Collection Manager Class which manage collection
     */
    public AddCommand(CollectionManager collectionManager) {
        super("add {element}",  "","add new item to collection");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (!stringArgument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException();
            TicketRaw ticketRaw = (TicketRaw) objectArgument;
            collectionManager.addToCollection(new Ticket(
                    collectionManager.generateNextId(),
                    ticketRaw.getName(),
                    ticketRaw.getCoordinates(),
                    ticketRaw.getPrice(),
                    ticketRaw.getTicketType(),
                    ticketRaw.getPerson()
            ));
            ResponseOutputer.appendln("Created new item successfully");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + "'");
        }
        return false;
    }
}
