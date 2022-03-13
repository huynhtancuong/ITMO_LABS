package itmo.test.ConcreteCommand;

import itmo.Commands.Command;

public class CommandOff implements Command {

    @Override
    public void execute() {
        System.out.println("Light off");
    }
}
