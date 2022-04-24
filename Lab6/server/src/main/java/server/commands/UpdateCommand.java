package server.commands;

import common.data.Coordinates;
import common.data.Person;
import common.data.Ticket;
import common.data.TicketType;
import common.exceptions.CollectionIsEmptyException;
import common.exceptions.TicketNotFoundException;
import common.exceptions.WrongAmountOfElementsException;
import common.interaction.TicketRaw;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;


/**
 * Command 'update'. Updates the information about selected ticket.
 */
public class UpdateCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    /**
     * @param collectionManager collection Manager
     */
    public UpdateCommand(CollectionManager collectionManager) {
        super("update <ID> {element}", "", "update the value of the collection element whose id is equal to the given");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        try {
            if (stringArgument.isEmpty() || objectArgument == null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            Long id = Long.parseLong(stringArgument);
            Ticket oldTicket = collectionManager.getById(id);
            if (oldTicket == null) throw new TicketNotFoundException();


            TicketRaw ticketRaw = (TicketRaw) objectArgument;
            String name = ticketRaw.getName() == null ? oldTicket.getName() : ticketRaw.getName();
            Coordinates coordinates = ticketRaw.getCoordinates() == null ? oldTicket.getCoordinates() : ticketRaw.getCoordinates();
            java.time.LocalDate creationDate = oldTicket.getCreationDate();
            Long price = ticketRaw.getPrice() == null ? oldTicket.getPrice() : ticketRaw.getPrice();
            TicketType type = ticketRaw.getTicketType() == null ? oldTicket.getTicketType() : ticketRaw.getTicketType();
            Person person = ticketRaw.getPerson() == null ? oldTicket.getPerson() : ticketRaw.getPerson();

            collectionManager.removeFromCollection(oldTicket);
            collectionManager.addToCollection(new Ticket(
                    oldTicket.getId(),
                    name,
                    coordinates,
                    price,
                    type,
                    person,
                    creationDate
            ));
            ResponseOutputer.appendln("Ticket changed successfully");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.appenderror("Collection is empty");
        } catch (NumberFormatException exception) {
            ResponseOutputer.appenderror("ID must be integer number");
        } catch (TicketNotFoundException exception) {
            ResponseOutputer.appenderror("Ticket with this ID can not be found");
        }
        return false;
    }
}
