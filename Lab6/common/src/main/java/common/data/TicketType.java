package common.data;

import java.io.Serializable;

/**
 * Creating a new type of object called `TicketType`.
 */
public enum TicketType implements Serializable {
    /**
     * VIP Ticket Type
     */
    VIP,
    /**
     * Usual ticket type
     */
    USUAL,
    /**
     * budgetary ticket type
     */
    BUDGETARY,
    /**
     * cheap ticket type
     */
    CHEAP;

    /**
     * Generates a beautiful list of enum string values.
     * @return String with all enum values splitted by comma.
     */
    public static String nameList() {
        String nameList = "";
        for (TicketType ticketType : values()) {
            nameList += ticketType.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
