package itmo.Client;

import itmo.Interface.Command;
import itmo.Invoker.CommandManager;
import itmo.ConcreteCommand.*;

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
