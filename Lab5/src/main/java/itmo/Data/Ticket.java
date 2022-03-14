package itmo.Data;

import java.time.LocalDate;

public class Ticket implements CSV, Comparable<Ticket> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше -1, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

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

    public TicketType getTicketType() {
        return type;
    }

    public Person getPerson() {
        return person;
    }
    public Ticket(){}
    public Ticket(Long id, String name, Coordinates coordinates, Long price, TicketType type, Person person, LocalDate creationDate) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.type = type;
        this.person = person;
        this.creationDate = creationDate;
    }

    public Ticket(Long id, String name, Coordinates coordinates, Long price, TicketType type, Person person ) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.type = type;
        this.person = person;
        this.creationDate = LocalDate.now();
    }

    @Override
    public int compareTo(Ticket ticketObj) {
        return (int) (this.getPrice() - ticketObj.getPrice());
    }

    @Override
    public String toString() {
        String info = "";
        info += "Ticket №" + id;
        info += " (created " + creationDate.toString() + ")";
        info += "\nName: " + name;
        info += "\nCoordinate: " + coordinates;
        info += "\nPrice: " + price;
        info += "\nType: " + type;
        info += "\nPerson: " + person;
        return info;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + coordinates.hashCode() +  price.hashCode() + type.hashCode() +
                person.hashCode() + creationDate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Ticket) {
            Ticket ticketObj = (Ticket) obj;
            return name.equals(ticketObj.getName()) && coordinates.equals(ticketObj.getCoordinates()) &&
                    (price == ticketObj.getPrice()) && (type == ticketObj.getTicketType()) &&
                    (person == ticketObj.getPerson()) && (creationDate == ticketObj.getCreationDate());
        }
        return false;
    }

    public static void main(String... args) {
        Coordinates coordinate = new Coordinates();
        TicketType ticketType = TicketType.CHEAP;
        Ticket ticket1 = new Ticket(Long.valueOf(1)," haha", coordinate, Long.valueOf(2), ticketType, new Person());
        System.out.println(ticket1.toString());
    }

}
