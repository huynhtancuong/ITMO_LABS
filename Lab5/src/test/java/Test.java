import itmo.Data.Person;
import itmo.Exceptions.IncorrectInputInScriptException;
import itmo.Utility.TicketAsker;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IncorrectInputInScriptException {
        Scanner userScanner = new Scanner(System.in);
        TicketAsker ticketAsker = new TicketAsker(userScanner);
//        String a = ticketAsker.askPassportID();
//        System.out.println(a);
//        System.out.println(ticketAsker.askBirthday());
        Person person = ticketAsker.askPerson();
        Person person1 = ticketAsker.askPerson();
        System.out.println(person);
        System.out.println(person1);
    }
}
