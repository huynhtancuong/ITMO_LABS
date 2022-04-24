package server.commands;

import common.data.Ticket;
import common.exceptions.WrongAmountOfElementsException;
import common.interaction.TicketRaw;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

/**
 * Command 'add_if_max'. Adds a new element to collection if it's greater than the greatest one.
 */
public class AddIfMaxCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    /**
     * @param collectionManager Collection Manager Class which manage collection
     */
    public AddIfMaxCommand(CollectionManager collectionManager) {
        super("add_if_max {element}",  "","add a new element if its value is less than that of the smallest");
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
            Ticket ticketToAdd = new Ticket(
                collectionManager.generateNextId(),
                ticketRaw.getName(),
                ticketRaw.getCoordinates(),
                ticketRaw.getPrice(),
                ticketRaw.getTicketType(),
                ticketRaw.getPerson()
            );

            if (collectionManager.collectionSize() == 0 || ticketToAdd.compareTo(collectionManager.getGreatestValue()) > 0) {
                collectionManager.addToCollection(ticketToAdd);
                ResponseOutputer.appendln("Item added successfully.");
                return true;
            } else ResponseOutputer.appenderror("The value of the item is lower than the value of the greatest of the items!");
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + "'");
        } catch (ClassCastException exception) {
            ResponseOutputer.appenderror("Переданный клиентом объект неверен!");
        }
        return false;
    }
}
