package server.utility;
import itmo.Data.Coordinates;
import itmo.Data.Person;
import itmo.Data.Ticket;
import itmo.Data.TicketType;
import itmo.Exceptions.DuplicatePassportID;
import itmo.Utility.Console;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;


/**
 * This class is responsible for reading and writing to a file
 */
public class CollectionFileManager {
    private static final String CSV_SEPARATOR = ",";
    private String envVariable;

    /**
     * @param envVariable environment variable
     */
    public CollectionFileManager(String envVariable) {
        this.envVariable = envVariable;
    }

   /*
   ** Writes collection to a file.
   * @param collection Collection to write.
    */
/*
    public void writeCollection(Collection<?> collection) {
        if (System.getenv(envVariable) != null) {
            try {
                File file = new File(System.getenv(envVariable));
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file, false);
                BufferedWriter bw = new BufferedWriter(fw);
                Iterator tickets = (Iterator) collection.iterator();

                for (Ticket ticket : tickets)
                {
                    StringBuffer oneLine = new StringBuffer();
                    oneLine.append(ticket.getCSVString(CSV_SEPARATOR));
                    bw.write(oneLine.toString());
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
*/
    /**
     * Write a collection of tickets to a file
     *
     * @param tickets The collection of tickets to write to the file.
     */
    public void writeCollection(HashSet<Ticket> tickets) {
        String path_to_file = System.getenv(envVariable);
        if (path_to_file!=null) {
            try {
                //Specify the file name and path here
                File file = new File(path_to_file);

                /* This logic is to create the file if the
                 * file is not already present
                 */
                if (!file.exists()) {
                    file.createNewFile();
                }

                //Here true is to append the content to file
                FileWriter fw = new FileWriter(file, false);
                //BufferedWriter writer give better performance
                //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path_to_file), "UTF-8"));
                BufferedWriter bw = new BufferedWriter(fw);

                for (Ticket ticket : tickets) {
                    StringBuffer oneLine = new StringBuffer();
                    oneLine.append(ticket.getCSVString(CSV_SEPARATOR));
                    bw.write(oneLine.toString());
                    bw.newLine();
                }
                bw.close();
            } catch (FileNotFoundException e) {
                itmo.Utility.Console.printError("File not found!");
                System.exit(0);
            } catch (UnsupportedEncodingException e) {
                itmo.Utility.Console.printError("Unsupported Encoding");
                System.exit(0);
            } catch (IOException e) {
                itmo.Utility.Console.printError("Don't have permission to write file");
                System.exit(0);
            }
        }
    }

    /**
     * Reads the file and creates a collection of tickets
     *
     * @return A HashSet of Tickets.
     */
    public HashSet<Ticket> readCollection() {
        HashSet<Ticket> tickets = new HashSet<Ticket>();
        Path pathToFile = null;
        try {
            pathToFile = Paths.get(System.getenv(envVariable));
        } catch (NullPointerException e) {
            itmo.Utility.Console.printError("Can not file environment variable");
        }

        if (pathToFile!=null) {
            //create an instance of BufferdReader
            //using try with resource
            try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
                //read the first line from the text file
                String line = br.readLine();
                //loop until all lines are read
                while (line != null) {
                    //use string.split to lead a string array with the value from each line of the file
                    String[] attributes = line.split(CSV_SEPARATOR);
                    Ticket ticket = createTicket(attributes);
                    //add the Ticket to hashset
                    tickets.add(ticket);
                    //read the next line before looping
                    //if end of file reached, line would be null
                    line = br.readLine();
                }
                return tickets;
            } catch (IOException e) {
                itmo.Utility.Console.printError("Error when reading the file. May be the program doesn't have permission to read it.");
            } catch (DuplicatePassportID e) {
                itmo.Utility.Console.printError("The data in file is not correct (PassportID is not correct)");
            } catch (ParseException e) {
                itmo.Utility.Console.printError("The data in file is not correct.");
            } catch (NumberFormatException e) {
                Console.printError("The data in file is not correct");
            }
        }
        return new HashSet<Ticket>();

    }

    private static Ticket createTicket(String[] metadata) throws ParseException, DuplicatePassportID {
        Long id = Long.parseLong(metadata[0]);
        String name = metadata[1];
        Coordinates coordinates = new Coordinates(Long.parseLong(metadata[2]), Long.parseLong(metadata[3]));
        LocalDate creationDate = LocalDate.parse(metadata[4]);
        Long price = Long.parseLong(metadata[5]);
        TicketType type = TicketType.valueOf(metadata[6]);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date birthday = formatter.parse(metadata[7]);
        long height = Long.parseLong(metadata[8]);
        String passportID = metadata[9];
        Person person = new Person(birthday, height, passportID);

        Ticket ticket = new Ticket(id, name, coordinates, price, type, person, creationDate);

        return ticket;
    }


    /**
     * It writes the collection of tickets to a CSV file.
     * @param args arguments
     */
    public static void main(String... args)
    {
        HashSet<Ticket> tickets = new HashSet<Ticket>();
        /*
        Coordinates coordinate = new Coordinates(Long.valueOf(2), Long.valueOf(3));
        Long price = Long.valueOf(3);
        TicketType type = TicketType.VIP;
        java.util.Date bithday = new java.util.Date();
        Person person1 = null;
        try {
            person1 = new Person(bithday, Long.valueOf(3), "C7652595");

        } catch (DuplicatePassportID e) {
            e.printStackTrace();
        }
        Person person2 = null;
        try {
            person2 = new Person(bithday, Long.valueOf(3), "C7652593");

        } catch (DuplicatePassportID e) {
            e.printStackTrace();
        }

        Ticket ticket1 = new Ticket("hhi", coordinate, price, type, person1);
        Ticket ticket2 = new Ticket("huhu", coordinate, price, type, person2);
        tickets.add(ticket1);
        tickets.add(ticket2);


         */
        CollectionFileManager collectionFileManager = new CollectionFileManager("HEHE");
        tickets = collectionFileManager.readCollection();
        for (Ticket ticket : tickets) {
            System.out.println(ticket.getCSVString(","));
        }
        System.out.println(tickets);
        //writeToCSV(tickets, "data.csv");
     }
}
