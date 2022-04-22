package common.interaction;

import common.data.*;

import java.io.Serializable;

/**
 * Class for get Tickets value.
 */
public class TicketRaw implements Serializable {
    private String name;
    private Coordinates coordinates;
    private Long price;
    private TicketType type;
    private Person person;

    public TicketRaw(String name, Coordinates coordinates, Long price, TicketType type,
                     Person person) {
        this.name = name;
        // A constructor.
        this.coordinates = coordinates;
        this.price = price;
        this.type = type;
        this.person = person;
    }

    /**
     * @return Name of the Ticket.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Coordinates of the Ticket.
     */
    public Coordinates getCoordinates() {
        return coordinates;
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

    @Override
    public String toString() {
        String info = "";
        info += "\nName: " + name;
        info += "\nCoordinate: " + coordinates;
        info += "\nPrice: " + price;
        info += "\nType: " + type;
        info += "\nPerson: " + person;
        return info;
    }

    @Override
    // A method that returns a hash code value for the object.
    public int hashCode() {
        return name.hashCode() + coordinates.hashCode() +  price.hashCode() + type.hashCode() +
                person.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Ticket) {
            Ticket ticketObj = (Ticket) obj;
            return name.equals(ticketObj.getName()) && coordinates.equals(ticketObj.getCoordinates()) &&
                    (price == ticketObj.getPrice()) && (type == ticketObj.getTicketType()) &&
                    (person == ticketObj.getPerson());
        }
        return false;
    }
}
