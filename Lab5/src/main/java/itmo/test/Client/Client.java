package itmo.test.Client;

import itmo.Commands.Command;
import itmo.test.Invoker.CommandManager;
import itmo.test.ConcreteCommand.CommandOff;
import itmo.test.ConcreteCommand.CommandOn;

public class Client {
    public static void main(String[] args) {

        Command commandOff = new CommandOff();
        Command commandOn = new CommandOn();
        new CommandManager(commandOn, commandOff);

        System.out.println("Username is: " + System.getProperty("username"));

        //dong goi request lai thanh mot
        /*
        String request = args[0];
        switch (request){
            case "Off":
            {
                remote.setCommand(commandOff);
                break;
            }
            case "On":
            {
                remote.setCommand(commandOn);
                break;
            }
        }
        */


        //remote.run();
    }
}
