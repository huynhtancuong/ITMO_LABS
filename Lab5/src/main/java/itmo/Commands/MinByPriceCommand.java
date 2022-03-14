package itmo.Commands;

import itmo.Exceptions.CollectionIsEmptyException;
import itmo.Exceptions.WrongAmountOfElementsException;
import itmo.Utility.CollectionManager;
import itmo.Utility.Console;

/**
 * Command 'min_by_price'. Prints the element of the collection with minimum price.
 */
public class MinByPriceCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public MinByPriceCommand(CollectionManager collectionManager) {
        super("min_by_price", "display any object from the collection whose price field value is the minimum");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Console.println(collectionManager.minByPrice());
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("Collection is empty");
        }
        return true;
    }
}
