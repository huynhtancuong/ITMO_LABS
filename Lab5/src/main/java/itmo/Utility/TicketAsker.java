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
                Console.printError("Имя не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printError("Name must not be empty");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }


    /**
     * Asks a user the marine's X coordinate.
     *
     * @return Marine's X coordinate.
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
                Console.printError("Координата X не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("Координата X должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }

    /**
     * Asks a user the marine's Y coordinate.
     *
     * @return Marine's Y coordinate.
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
                Console.printError("Координата Y не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }

    /**
     * Asks a user the marine's coordinates.
     *
     * @return Marine's coordinates.
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
     * Asks a user the marine's price.
     *
     * @return Marine's price.
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
                Console.printError("Здоровье не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printError("Здоровье должно быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("Здоровье должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
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
                Console.printError("Здоровье не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printError("Здоровье должно быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printError("Здоровье должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return height;
    }

    /**
     * Asks a user the marine's weapon type.
     *
     * @return Marine's weapon type.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public TicketType askTicketType() throws IncorrectInputInScriptException {
        String strTicketType;
        TicketType ticketType;
        while (true) {
            try {
                Console.println("Список оружия дальнего боя - " + TicketType.nameList());
                Console.println("Введите оружие дальнего боя:");
                Console.print(App.PS2);
                strTicketType = userScanner.nextLine().trim();
                if (fileMode) Console.println(strTicketType);
                ticketType = TicketType.valueOf(strTicketType.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Оружие не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printError("Оружия нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return ticketType;
    }

    public String askPassportID() throws IncorrectInputInScriptException {
        String strPassportID;
        while (true) {
            try {
                Console.println("Enter PassportID: ");
                Console.print(App.PS2);
                strPassportID = userScanner.nextLine().trim();
                if (fileMode) Console.println(strPassportID);
                if (strPassportID.equals("")) throw new MustBeNotEmptyException();
                if (strPassportID.length() < PASSPORTID_DOWN_LIMIT) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printError("Имя не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printError("Имя не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printError("Непредвиденная ошибка!");
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

    public Person askPerson() throws IncorrectInputInScriptException {
        String passportID;
        Date birthday;
        long height;
        Person person = new Person();
        while(true) {
            try {
                passportID = askPassportID();
                person.setPassportID(passportID);
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
