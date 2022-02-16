package itmo.ConcreteCommand;

import itmo.Interface.Command;

public class CommandOn implements Command {

    @Override
    public void execute() {
        System.out.println("Light On");
    }
}
