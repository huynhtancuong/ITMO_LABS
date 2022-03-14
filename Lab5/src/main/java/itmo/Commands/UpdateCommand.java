package itmo.Commands;

import itmo.Data.Coordinates;
import itmo.Data.Person;
import itmo.Data.Ticket;
import itmo.Data.TicketType;
import itmo.Exceptions.CollectionIsEmptyException;
import itmo.Exceptions.IncorrectInputInScriptException;
import itmo.Exceptions.TicketNotFoundException;
import itmo.Exceptions.WrongAmountOfElementsException;
import itmo.Utility.CollectionManager;
import itmo.Utility.Console;
import itmo.Utility.TicketAsker;


/**
 * Command 'update'. Updates the information about selected marine.
 */
public class UpdateCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private TicketAsker ticketAsker;

    /**
     * @param collectionManager collection Manager
     * @param ticketAsker ticket Asker
     */
    public UpdateCommand(CollectionManager collectionManager, TicketAsker ticketAsker) {
        super("update <ID> {element}", "update the value of the collection element whose id is equal to the given");
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
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            Long id = Long.parseLong(argument);
            Ticket oldTicket = collectionManager.getById(id);
            if (oldTicket == null) throw new TicketNotFoundException();

            String name = oldTicket.getName();
            Coordinates coordinates = oldTicket.getCoordinates();
            java.time.LocalDate creationDate = oldTicket.getCreationDate();
            Long price = oldTicket.getPrice();
            TicketType type = oldTicket.getTicketType();
            Person person = oldTicket.getPerson();


            collectionManager.removeFromCollection(oldTicket);

            if (ticketAsker.askQuestion("Do you want to change name?")) name = ticketAsker.askName();
            if (ticketAsker.askQuestion("Do you want to change coordinates?")) coordinates = ticketAsker.askCoordinates();
            if (ticketAsker.askQuestion("Do you want to change price?")) price = ticketAsker.askPrice();
            if (ticketAsker.askQuestion("Do you want to change ticket type?")) type = ticketAsker.askTicketType();
            if (ticketAsker.askQuestion("Do you want to change person?")) person = ticketAsker.askPerson(true);
            collectionManager.addToCollection(new Ticket(
                    oldTicket.getId(),
                    name,
                    coordinates,
                    price,
                    type,
                    person,
                    creationDate
            ));
            Console.println("Ticket changed successfully");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("Collection is empty");
        } catch (NumberFormatException exception) {
            Console.printError("ID must be number");
        } catch (TicketNotFoundException exception) {
            Console.printError("Ticket with this ID can not be found");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
