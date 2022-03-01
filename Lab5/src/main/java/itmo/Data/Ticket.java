package itmo.Data;

import java.time.LocalDate;

public class Ticket {
    private static Long max_id = Long.valueOf(0);

    private Long id; //Поле не может быть null, Значение поля должно быть больше -1, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long price; //Поле не может быть null, Значение поля должно быть больше -1
    private TicketType type; //Поле может быть null
    private Person person; //Поле не может быть null

    public String getCSVString(String CSV_SEPARATOR) {
        return      id.toString()   +   CSV_SEPARATOR
                +   name            +   CSV_SEPARATOR
                +   coordinates.getCSVString(CSV_SEPARATOR) + CSV_SEPARATOR
                +   creationDate.toString() + CSV_SEPARATOR
                +   price.toString()    + CSV_SEPARATOR
                +   type            + CSV_SEPARATOR
                +   person.getCSVString(CSV_SEPARATOR);
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Long getPrice() {
        return price;
    }

    public TicketType getType() {
        return type;
    }

    public Person getPerson() {
        return person;
    }
    public Ticket(){}
    public Ticket(String name, Coordinates coordinates, Long price, TicketType type, Person person) {
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.type = type;
        this.person = person;
        this.id = max_id++;
        this.creationDate = LocalDate.now();
    }



    public static void main(String... args) {
        Coordinates coordinate = new Coordinates();
        TicketType ticketType = TicketType.CHEAP;
        Ticket ticket1 = new Ticket("haha", coordinate, Long.valueOf(2), ticketType, new Person());
        System.out.println(ticket1.id);
        System.out.println(ticket1.creationDate);
    }

}
