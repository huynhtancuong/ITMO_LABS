package server.commands;

import common.exceptions.CollectionIsEmptyException;
import common.exceptions.WrongAmountOfElementsException;
import server.utility.CollectionManager;
import server.utility.ResponseOutputer;

/**
 * Command 'filter_by_price'. Filters the collection by price.
 */
public class FilterByPriceCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    /**
     * Constructor
     * @param collectionManager instance of CollectionManager
     */
    public FilterByPriceCommand(CollectionManager collectionManager) {
        super("filter_by_price <Price>", "", "display elements whose price field value is greater than the specified one");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String stringArgument, Object objectArgument) {
        Long price = null;
        try {
            if (stringArgument.isEmpty() || objectArgument != null) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();

            price = Long.parseLong(stringArgument);
            String filteredInfo = collectionManager.priceFilteredInfo(price);
            if (!filteredInfo.isEmpty()) {
                ResponseOutputer.appendln(filteredInfo);
                return true;
            } else ResponseOutputer.appendln("There are no elements whose price field value is greater than the specified one");
        } catch (WrongAmountOfElementsException exception) {
            ResponseOutputer.appendln("Usage: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            ResponseOutputer.appenderror("Collection is empty");
        } catch (IllegalArgumentException exception) {
            ResponseOutputer.appenderror("Price must be integer number");
        }
        return false;
    }
}
