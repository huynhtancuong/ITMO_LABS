package itmo.Utility;

import itmo.Exceptions.*;
import itmo.Commands.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Operates the commands.
 */
public class CommandManager {
    private final int COMMAND_HISTORY_SIZE = 8;

    private String[] commandHistory = new String[COMMAND_HISTORY_SIZE];
    private List<Command> commands = new ArrayList<>();

    public void setHelpCommand(Command helpCommand) {
        this.helpCommand = helpCommand;
        commands.add(helpCommand);
    }

    public void setInfoCommand(Command infoCommand) {
        this.infoCommand = infoCommand;
        commands.add(infoCommand);
    }

    public void setShowCommand(Command showCommand) {
        this.showCommand = showCommand;
        commands.add(showCommand);
    }

    public void setAddCommand(Command addCommand) {
        this.addCommand = addCommand;
        commands.add(addCommand);
    }

    public void setUpdateCommand(Command updateCommand) {
        this.updateCommand = updateCommand;
        commands.add(updateCommand);
    }

    public void setRemoveByIdCommand(Command removeByIdCommand) {
        this.removeByIdCommand = removeByIdCommand;
        commands.add(removeByIdCommand);
    }

    public void setClearCommand(Command clearCommand) {
        this.clearCommand = clearCommand;
        commands.add(clearCommand);
    }

    public void setSaveCommand(Command saveCommand) {
        this.saveCommand = saveCommand;
        commands.add(saveCommand);
    }

    public void setExitCommand(Command exitCommand) {
        this.exitCommand = exitCommand;
        commands.add(exitCommand);
    }

    public void setExecuteScriptCommand(Command executeScriptCommand) {
        this.executeScriptCommand = executeScriptCommand;
        commands.add(executeScriptCommand);
    }

    public void setAddIfMaxCommand(Command addIfMaxCommand) {
        this.addIfMaxCommand = addIfMaxCommand;
        commands.add(addIfMaxCommand);
    }

    public void setRemoveGreaterCommand(Command removeGreaterCommand) {
        this.removeGreaterCommand = removeGreaterCommand;
        commands.add(removeGreaterCommand);
    }

    public void setHistoryCommand(Command historyCommand) {
        this.historyCommand = historyCommand;
        commands.add(historyCommand);
    }

    public void setSumOfHealthCommand(Command sumOfHealthCommand) {
        this.sumOfHealthCommand = sumOfHealthCommand;
        commands.add(sumOfHealthCommand);
    }

    public void setMinByPriceCommand(Command minByPriceCommand) {
        this.minByPriceCommand = minByPriceCommand;
        commands.add(minByPriceCommand);
    }

    public void setFilterByPriceCommand(Command filterByPriceCommand) {
        this.filterByPriceCommand = filterByPriceCommand;
        commands.add(filterByPriceCommand);
    }

    public void setRemoveLowerCommand(Command removeLowerCommand) {
        this.removeLowerCommand = removeLowerCommand;
        commands.add(removeLowerCommand);
    }

    public void setRemoveByPersonCommand(Command removeAllByPersonCommand) {
        this.removeAllByPersonCommand = removeAllByPersonCommand;
        commands.add(removeAllByPersonCommand);
    }

    private Command helpCommand;
    private Command infoCommand;
    private Command showCommand;
    private Command addCommand;
    private Command updateCommand;
    private Command removeByIdCommand;
    private Command clearCommand;
    private Command saveCommand;
    private Command exitCommand;
    private Command executeScriptCommand;
    private Command addIfMaxCommand;
    private Command removeGreaterCommand;
    private Command removeLowerCommand;
    private Command historyCommand;
    private Command sumOfHealthCommand;
    private Command minByPriceCommand;
    private Command filterByPriceCommand;
    private Command removeAllByPersonCommand;

    public CommandManager() {}

    /**
     * @return The command history.
     */
    public String[] getCommandHistory() {
        return commandHistory;
    }

    /**
     * @return List of manager's commands.
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Adds command to command history.
     * @param commandToStore Command to add.
     */
    public void addToHistory(String commandToStore) {

        for (Command command : commands) {
            if (command.getName().split(" ")[0].equals(commandToStore)) {
                for (int i = COMMAND_HISTORY_SIZE-1; i>0; i--) {
                    commandHistory[i] = commandHistory[i-1];
                }
                commandHistory[0] = commandToStore;
            }
        }
    }


    /**
     * Prints that command is not found.
     * @param command Comand, which is not found.
     * @return Command exit status.
     */
    public boolean noSuchCommand(String command) {
        Console.println("Command '" + command + "' not found. Type 'help' for help.");
        return false;
    }
    
    /**
     * Prints info about the all commands.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean help(String argument) {
        if (helpCommand.execute(argument)) {
            for (Command command : commands) {
                Console.printTable(command.getName(), command.getDescription());
            }
            return true;
        } else return false;
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean show(String argument) {
        return showCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean add(String argument) {
        return addCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean update(String argument) {
        return updateCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeById(String argument) {
        return removeByIdCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean exit(String argument) {
        return exitCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean addIfMax(String argument) {
        return addIfMaxCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean removeGreater(String argument) {
        return removeGreaterCommand.execute(argument);
    }

    public boolean removeLower(String argument) {
        return removeLowerCommand.execute(argument);
    }


    public boolean removeAllByPerson(String argument) {
        return removeAllByPersonCommand.execute(argument);
    }


    /**
     * Prints the history of used commands.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean history(String argument) {
        if (historyCommand.execute(argument)) {
            try {
                if (commandHistory.length == 0) throw new HistoryIsEmptyException();
    
                Console.println("Последние использованные команды:");
                for (int i=0; i<commandHistory.length; i++) {
                    if (commandHistory[i] != null) Console.println(" " + commandHistory[i]);
                }
                return true;
            } catch (HistoryIsEmptyException exception) {
                Console.println("Ни одной команды еще не было использовано!");
            }
        }
        return false;
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean sumOfHealth(String argument) {
        return sumOfHealthCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean minByPrice(String argument) {
        return minByPriceCommand.execute(argument);
    }

    /**
     * Executes needed command.
     * @param argument Its argument.
     * @return Command exit status.
     */
    public boolean filterByPrice(String argument) {
        return filterByPriceCommand.execute(argument);
    }

    @Override
    public String toString() {
        return "CommandManager (вспомогательный класс для работы с командами)";
    }
}
