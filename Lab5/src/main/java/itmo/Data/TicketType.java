package itmo.Data;

public enum TicketType {
    VIP,
    USUAL,
    BUDGETARY,
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
