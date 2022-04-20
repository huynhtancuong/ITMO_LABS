import itmo.Data.Person;
import itmo.Exceptions.IncorrectInputInScriptException;
import itmo.Utility.TicketAsker;

import java.util.Scanner;

/**
 * It asks the user for a person's information.
 */
public class Test {
    /**
     * Ask the user for a person's information, and return a Person object
     * @param args arguments
     * @throws IncorrectInputInScriptException an exception
     */
    public static void main(String[] args) throws IncorrectInputInScriptException {
        Scanner userScanner = new Scanner(System.in);
        TicketAsker ticketAsker = new TicketAsker(userScanner);
//        String a = ticketAsker.askPassportID();
//        System.out.println(a);
//        System.out.println(ticketAsker.askBirthday());
        Person person = ticketAsker.askPerson(false);
        Person person1 = ticketAsker.askPerson(false);
        System.out.println(person);
        System.out.println(person1);
    }
}
