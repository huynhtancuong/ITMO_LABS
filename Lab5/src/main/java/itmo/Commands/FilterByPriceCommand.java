package itmo.Commands;

import itmo.Exceptions.CollectionIsEmptyException;
import itmo.Exceptions.WrongAmountOfElementsException;
import itmo.Utility.CollectionManager;
import itmo.Utility.Console;

/**
 * Command 'filter_by_price'. Filters the collection by price.
 */
public class FilterByPriceCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public FilterByPriceCommand(CollectionManager collectionManager) {
        super("filter_by_price <Price>", "display elements whose price field value is greater than the specified one");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        Long price = null;
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            price = Long.parseLong(argument);
            String filteredInfo = collectionManager.priceFilteredInfo(price);
            if (!filteredInfo.isEmpty()) {
                Console.println(filteredInfo);
                return true;
            } else Console.println("There are no elements whose price field value is greater than the specified one");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printError("Collection is empty");
        } catch (IllegalArgumentException exception) {
            Console.printError("Price must be number");
        }
        return false;
    }
}
