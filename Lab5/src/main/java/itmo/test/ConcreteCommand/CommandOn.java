package itmo.test.ConcreteCommand;

import itmo.Commands.Command;

public class CommandOn implements Command {

    @Override
    public void execute() {
        System.out.println("Light On");
    }
}
