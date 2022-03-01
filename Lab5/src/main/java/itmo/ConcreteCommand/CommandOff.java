package itmo.ConcreteCommand;

import itmo.Interface.Command;

public class CommandOff implements Command {

    @Override
    public void execute() {
        System.out.println("Light off");
    }
}
