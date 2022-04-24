package client.utility;

import client.App;
import common.data.Coordinates;
import common.data.Person;
import common.data.TicketType;
import common.exceptions.DuplicatePassportID;
import common.exceptions.IncorrectInputInScriptException;
import common.exceptions.MustBeNotEmptyException;
import common.exceptions.NotInDeclaredLimitsException;
import common.utility.Outputer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * TicketAsker class is a class that asks a user the ticket's name, coordinates, price, type, passport ID, birthday and
 * height
 */
public class TicketAsker {
    //Some data validation here
    private final int HEIGHT_DOWN_LIMIT = 0;
    private final int PASSPORTID_DOWN_LIMIT = 10;
    private final int PRICE_DOWN_LIMIT = 0;
    private final String DATE_PATTERN = "dd-MM-yyyy";

    /**
     * Just a Scanner
     */
    //
    private Scanner userScanner;
    /**
     * Just a file Mode
     */
    private boolean fileMode;
    /**
     * Just a date formatter
     */
    private final SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
    /**
     * @param userScanner Scanner
     */
    public TicketAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    /**
     * @param userScanner Scanner
     */
    //Sets a scanner to scan user input
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * @return Scanner
     */
    // Return Scanner, which uses for user input
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Setter
     */
    // Set ticket asker mode to "file mode"
    public void setFileMode() {
        fileMode = true;
    }

    /**
     * Setter
     */
    // Set ticket asker mode to "User mode"
    public void setUserMode() {
        fileMode = false;
    }

    /**
     * @return a name
     * @throws IncorrectInputInScriptException an exception
     */
    // Ask a user the ticket name
    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Outputer.println("Enter name: ");
                Outputer.print(App.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Name is not recognized");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Outputer.printerror("Name must not be empty");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Outputer.printerror("Unexpected error");
                System.exit(0);
            }
        }
        return name;
    }


    /**
     * Asks a user the ticket's X coordinate.
     *
     * @return Ticket's X coordinate.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public long askX() throws IncorrectInputInScriptException {
        String strX;
        long x;
        while (true) {
            try {
                Outputer.println("Enter X: ");
                Outputer.print(App.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(strX);
                x = Long.parseLong(strX);
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("X coordinate not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Outputer.printerror("The X coordinate must be represented by a integer number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Outputer.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return x;
    }

    /**
     * Asks a user the ticket's Y coordinate.
     *
     * @return Ticket's Y coordinate.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Long askY() throws IncorrectInputInScriptException {
        String strY;
        Long y;
        while (true) {
            try {
                Outputer.println("Enter Y: ");
                Outputer.print(App.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(strY);
                y = Long.parseLong(strY);
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Y coordinate not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Outputer.printerror("The Y coordinate must be represented by a integer number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Outputer.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return y;
    }

    /**
     * Asks a user the ticket's coordinates.
     *
     * @return Ticket's coordinates.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        long x;
        Long y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }


    /**
     * Asks a user the ticket's price.
     *
     * @return Ticket's price.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Long askPrice() throws IncorrectInputInScriptException {
        String strHealth;
        Long price;
        while (true) {
            try {
                Outputer.println("Enter price: ");
                Outputer.print(App.PS2);
                strHealth = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(strHealth);
                price = Long.parseLong(strHealth);
                if (price < PRICE_DOWN_LIMIT) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Price not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Outputer.printerror("Price must be greater than zero!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Outputer.printerror("Price must be represented by a integer number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Outputer.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return price;
    }

    /**
     * The function asks the user to enter a height, and then checks if the height is in the declared limits. If it is, the
     * function returns the height as a Long. If it isn't, the function prints an error message and asks the user to enter
     * the height again
     *
     * @return The height.
     * @throws IncorrectInputInScriptException an exception
     */
    public Long askHeight() throws IncorrectInputInScriptException {
        String strHeight;
        Long height;
        while (true) {
            try {
                Outputer.println("Enter height: ");
                Outputer.print(App.PS2);
                strHeight = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(strHeight);
                height = Long.parseLong(strHeight);
                if (height < HEIGHT_DOWN_LIMIT) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Height not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Outputer.printerror("Height must be greater than zero!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Outputer.printerror("Height must be represented by a integer number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Outputer.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return height;
    }

    /**
     * Asks a user the ticket's  type.
     *
     * @return Ticket's  type.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public TicketType askTicketType() throws IncorrectInputInScriptException {
        String strTicketType;
        TicketType ticketType;
        while (true) {
            try {
                Outputer.println("List of ticket types - " + TicketType.nameList());
                Outputer.println("Please enter ticket types:");
                Outputer.print(App.PS2);
                strTicketType = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(strTicketType);
                ticketType = TicketType.valueOf(strTicketType.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Ticket type is not recognized");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Outputer.printerror("Ticket type is not listed");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Outputer.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return ticketType;
    }

    /**
     * Ask for a passport ID
     *
     * @return String
     * @throws IncorrectInputInScriptException an exception
     */
    public String askPassportID() throws IncorrectInputInScriptException {
        String strPassportID;
        while (true) {
            try {
                Outputer.println("Enter Passport ID: ");
                Outputer.print(App.PS2);
                strPassportID = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(strPassportID);
                if (strPassportID.equals("")) throw new MustBeNotEmptyException();
                if (strPassportID.length() < PASSPORTID_DOWN_LIMIT) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Passport ID not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Outputer.printerror("Passport ID cannot be empty!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Outputer.printerror("Unexpected error!");
                System.exit(0);
            } catch (NotInDeclaredLimitsException e) {
                Outputer.printerror("Length of Passport ID must be greater than " + PASSPORTID_DOWN_LIMIT);
            }
        }
        return strPassportID;
    }

    /**
     * Ask the user to enter a birthday and return it
     *
     * @return birthday.
     * @throws IncorrectInputInScriptException an exception
     */
    public Date askBirthday() throws IncorrectInputInScriptException {
        String strBirthday;
        Date birthday;
        while (true) {
            try {
                Outputer.println("Enter birthday ("+DATE_PATTERN+ "): ");
                Outputer.print(App.PS2);
                strBirthday = userScanner.nextLine().trim();
                if (strBirthday==null) throw new NullPointerException();
                if (fileMode) Outputer.println(strBirthday);
                formatter.setLenient(false);
                birthday = formatter.parse(strBirthday);
                break;
            } catch (ParseException e) {
                Outputer.printerror("Entered birthday does not have the right format.");
            } catch (NullPointerException e) {
                Outputer.printerror("Birthday can not be null");
            }
        }
        return birthday;
    }


    /**
     * Asks a user a question.
     * @return Answer (true/false).
     * @param question A question.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Outputer.println(finalQuestion);
                Outputer.print(App.PS2);
                answer = userScanner.nextLine().trim();
                if (fileMode) Outputer.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Outputer.printerror("Answer is not recognized ");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Outputer.printerror("Answer must be '+' or '-'.");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Outputer.printerror("Unexpected error!");
                System.exit(0);
            }
        }
        return answer.equals("+");
    }


    /**
     * @param bypassDuplicatedPassportID boolean condition
     * @return Person
     * @throws IncorrectInputInScriptException an exception
     */
    public Person askPerson(boolean bypassDuplicatedPassportID) throws IncorrectInputInScriptException {
        String passportID;
        Date birthday;
        long height;
        Person person = new Person();
        while(true) {
            try {
                passportID = askPassportID();
                person.setPassportID(passportID, bypassDuplicatedPassportID);
                birthday = askBirthday();
                person.setBirthday(birthday);
                height = askHeight();
                person.setHeight(height);
                break;
            } catch (DuplicatePassportID e) {
                Outputer.printerror("Passport is unique");
            }
        }
        return person;
    }

    @Override
    public String toString() {
        return "TicketAsker class";
    }
}
