package itmo.Client;

import itmo.Interface.Command;
import itmo.Invoker.RemoteControl;
import itmo.ConcreteCommand.*;

import java.rmi.Remote;

public class Client {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();
        Command commandOff = new CommandOff();
        Command commandOn = new CommandOn();

        //dong goi request lai thanh mot
        int request = 0;
        switch (request){
            case 0:
            {
                remote.setCommand(commandOff);
                break;
            }
            case 1:
            {
                remote.setCommand(commandOn);
                break;
            }
        }

        remote.run();
    }
}
