package common.data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * It represents a ticket.
 */
public class Ticket implements CSV, Comparable<Ticket> , Serializable {
    public void setId(Long id) {
        this.id = id;
    }

    private Long id; //Поле не может быть null, Значение поля должно быть больше -1, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    /**
     * @param creationDate creationDate
     */
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

    /**
     * @return id
     */
    // A getter method for the field `id`.
    public Long getId() {
        return id;
    }

    /**
     * @return name
     */
    // A getter method for the field `name`.
    public String getName() {
        return name;
    }

    /**
     * @return coordinates
     */
    // A getter method for the field `coordinates`.
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return creationDate
     */
    // A getter method for the field `creationDate`.
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * It returns the name of the person.
     *
     * @return The name of the person.
     */
    public Long getPrice() {
        return price;
    }

    /**
     * A getter method for the field `type`.
     * @return tickettype
     */
    public TicketType getTicketType() {
        return type;
    }

    /**
     * A getter method for the field `person`.
     * @return person
     */

    public Person getPerson() {
        return person;
    }
    /**
     * Get the creation date of the object
     * @param name name
     * @param coordinates coordinates
     * @param creationDate creationDate
     * @param id id
     * @param person person
     * @param price price
     * @param type ticket type
     */
    public Ticket(Long id, String name, Coordinates coordinates, Long price, TicketType type, Person person, LocalDate creationDate) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.type = type;
        this.person = person;
        this.creationDate = creationDate;
    }
    /**
     * Get the creation date of the object
     * @param name name
     * @param coordinates coordinates
     * @param id id
     * @param person person
     * @param price price
     * @param type ticket type
     */
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
        info += "\nPrice: " + price + "$";
        info += "\nType: " + type;
        info += "\nPerson: " + person;
        return info;
    }

    /**
     * @return hascode
     */
    @Override
    public int hashCode() {
        return name.hashCode() + coordinates.hashCode() +  price.hashCode() + type.hashCode() +
                person.hashCode() + creationDate.hashCode();
    }

    /**
     * @param obj objecto to compare
     * @return true or false
     */
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


}
