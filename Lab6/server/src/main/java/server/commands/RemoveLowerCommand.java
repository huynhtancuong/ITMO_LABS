package server.commands;

import common.data.Ticket;
import common.exceptions.CollectionIsEmptyException;
import common.exceptions.WrongAmountOfElementsException;
import common.interaction.TicketRaw;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

/**
 * Command 'remove_lower'. Removes elements lower than user entered.
 */
public class RemoveLowerCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    /**
     * @param collectionManager Instance of Collection Manager
     */
    public RemoveLowerCommand(CollectionManager collectionManager) {
        super("remove_lower {element}", "", "remove all items from the collection that are less than the given one");

        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (!stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            TicketRaw ticketRaw = (TicketRaw) objectArgument;
            Ticket ticketToFind = new Ticket(
                    collectionManager.generateNextId(),
                    ticketRaw.getName(),
                    ticketRaw.getCoordinates(),
                    ticketRaw.getPrice(),
                    ticketRaw.getTicketType(),
                    ticketRaw.getPerson()
            );
            collectionManager.removeLower(ticketToFind);
            ResponseOutputer.appendln("Removed item successfully");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.appenderror("Collection is empty");
        }
        return false;
    }
}
