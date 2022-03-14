package itmo.Utility;

import itmo.Data.Coordinates;
import itmo.Data.Person;
import itmo.Data.TicketType;
import itmo.Exceptions.DuplicatePassportID;
import itmo.Exceptions.IncorrectInputInScriptException;
import itmo.Exceptions.MustBeNotEmptyException;
import itmo.Exceptions.NotInDeclaredLimitsException;
import itmo.run.App;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TicketAsker {
    //Some data validation here
    private int HEIGHT_DOWN_LIMIT = 0;
    private int PASSPORTID_DOWN_LIMIT = 10;
    private int PRICE_DOWN_LIMIT = 0;
    private String DATE_PATTERN = "dd-MM-yyyy";

    private Scanner userScanner;
    private boolean fileMode;
    private SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);

    public TicketAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    //Sets a scanner to scan user input
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    // Return Scanner, which uses for user input
    public Scanner getUserScanner() {
        return userScanner;
    }

    // Set ticket asker mode to "file mode"
    public void setFileMode() {
        fileMode = true;
    }

    // Set ticket asker mode to "User mode"
    public void setUserMode() {
        fileMode = false;
    }

    // Ask a user the ticket name
    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.println("Enter name: ");
                Console.print(App.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Name is not recognized");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printError("Name must not be empty");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Unexpected error");
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
                Console.println("Enter X: ");
                Console.print(App.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Long.parseLong(strX);
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("X coordinate not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("The X coordinate must be represented by a number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
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
                Console.println("Enter Y: ");
                Console.print(App.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println(strY);
                y = Long.parseLong(strY);
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Y coordinate not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("The Y coordinate must be represented by a number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
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
                Console.println("Enter price: ");
                Console.print(App.PS2);
                strHealth = userScanner.nextLine().trim();
                if (fileMode) Console.println(strHealth);
                price = Long.parseLong(strHealth);
                if (price < PRICE_DOWN_LIMIT) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Price not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printError("Price must be greater than zero!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("Price must be represented by a number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return price;
    }

    public Long askHeight() throws IncorrectInputInScriptException {
        String strHeight;
        Long height;
        while (true) {
            try {
                Console.println("Enter height: ");
                Console.print(App.PS2);
                strHeight = userScanner.nextLine().trim();
                if (fileMode) Console.println(strHeight);
                height = Long.parseLong(strHeight);
                if (height < HEIGHT_DOWN_LIMIT) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Height not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printError("Height must be greater than zero!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("Height must be represented by a number!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Unexpected error!");
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
                Console.println("List of ticket types - " + TicketType.nameList());
                Console.println("Please enter ticket types:");
                Console.print(App.PS2);
                strTicketType = userScanner.nextLine().trim();
                if (fileMode) Console.println(strTicketType);
                ticketType = TicketType.valueOf(strTicketType.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Ticket type is not recognized");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printError("Ticket type is not listed");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return ticketType;
    }

    public String askPassportID() throws IncorrectInputInScriptException {
        String strPassportID;
        while (true) {
            try {
                Console.println("Enter Passport ID: ");
                Console.print(App.PS2);
                strPassportID = userScanner.nextLine().trim();
                if (fileMode) Console.println(strPassportID);
                if (strPassportID.equals("")) throw new MustBeNotEmptyException();
                if (strPassportID.length() < PASSPORTID_DOWN_LIMIT) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Passport ID not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printError("Passport ID cannot be empty!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            } catch (NotInDeclaredLimitsException e) {
                Console.printError("Length of Passport ID must be greater than " + PASSPORTID_DOWN_LIMIT);
            }
        }
        return strPassportID;
    }

    public Date askBirthday() throws IncorrectInputInScriptException {
        String strBirthday;
        Date birthday;
        while (true) {
            try {
                Console.println("Enter birthday ("+DATE_PATTERN+ "): ");
                Console.print(App.PS2);
                strBirthday = userScanner.nextLine().trim();
                if (strBirthday==null) throw new NullPointerException();
                if (fileMode) Console.println(strBirthday);
                birthday = formatter.parse(strBirthday);
                break;
            } catch (ParseException e) {
                Console.printError("Entered birthday does not have the right format.");
            } catch (NullPointerException e) {
                Console.printError("Birthday can not be null");
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
                Console.println(finalQuestion);
                Console.print(App.PS2);
                answer = userScanner.nextLine().trim();
                if (fileMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Answer is not recognized ");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printError("Answer must be '+' or '-'.");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Unexpected error!");
                System.exit(0);
            }
        }
        return (answer.equals("+")) ? true : false;
    }


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
                Console.printError("Passport is unique");
            }
        }
        return person;
    }

    @Override
    public String toString() {
        return "TicketAsker class";
    }
}
