package itmo.Commands;

import itmo.Data.Person;
import itmo.Data.Ticket;
import itmo.Exceptions.CollectionIsEmptyException;
import itmo.Exceptions.IncorrectInputInScriptException;
import itmo.Exceptions.TicketNotFoundException;
import itmo.Exceptions.WrongAmountOfElementsException;
import itmo.Utility.CollectionManager;
import itmo.Utility.Console;
import itmo.Utility.TicketAsker;

/**
 * Command 'remove_greater'. Removes elements greater than user entered.
 */
public class RemoveByPersonCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private TicketAsker ticketAsker;

    public RemoveByPersonCommand(CollectionManager collectionManager, TicketAsker ticketAsker) {
        super("remove_all_by_person {element}", "remove all ticket of this person");
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
            Person personToFind = ticketAsker.askPerson(true);

            Ticket ticketFromCollection = collectionManager.getByPerson(personToFind);
            if (ticketFromCollection == null) throw new TicketNotFoundException();
            collectionManager.removeFromCollection(ticketFromCollection);
            Console.println("Items removed successfully");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("Collection is empty");
        } catch (TicketNotFoundException exception) {
            Console.printError("Can not find this person in collection");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
