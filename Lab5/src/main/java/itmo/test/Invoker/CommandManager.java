package itmo.test.Invoker;

import itmo.Commands.Command;

public class CommandManager {
    private Command command;
    public Command commandOff;
    public Command commandOn;

    public CommandManager(Command commandOn, Command commandOff) {
        this.commandOff = commandOff;
        this.commandOn  = commandOn;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void run() {
        command.execute();
    }
}
