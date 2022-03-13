package itmo.Utility;

import java.util.ArrayList;
import java.util.List;

import itmo.Commands.Command;

public class CommandManager {
    private final int COMMAND_HISTORY_SIZE = 8;

    private String[] commandHistory = new String[COMMAND_HISTORY_SIZE];
    private List<Command> commands = new ArrayList<>();
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
    private Command addIfMinCommand;
    private Command removeGreaterCommand;
    private Command historyCommand;
    private Command sumOfHealthCommand;
    private Command maxByMeleeWeaponCommand;
    private Command filterByWeaponTypeCommand;



    public void addToHistory(String commandToStore) {
        for (Command command : commands) {
            for (int i = COMMAND_HISTORY_SIZE-1; i>0; i--) {
                commandHistory[i] = commandHistory[i-1];
            }
            commandHistory[0] = commandToStore;
        }
    }

}
