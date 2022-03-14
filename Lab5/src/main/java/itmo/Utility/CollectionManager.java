package itmo.Utility;

import itmo.Data.*;
import itmo.Exceptions.CollectionIsEmptyException;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Operates the collection itself.
 */
public class CollectionManager {
    private HashSet<Ticket> ticketsCollection =  new HashSet<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;

    /**
     * @param fileManager File Manager class for managing the file
     */
    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;
        
        loadCollection();
    }

    /**
     * @return The collecton itself.
     */
    public HashSet<Ticket> getCollection() {
        return ticketsCollection;
    }

    /**
     * @return Last initialization time or null if there wasn't initialization.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Last save time or null if there wasn't saving.
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * @return Name of the collection's type.
     */
    public String collectionType() {
        return ticketsCollection.getClass().getName();
    }

    /**
     * @return Size of the collection.
     */
    public int collectionSize() {
        return ticketsCollection.size();
    }

    /**
     * @return The first element of the collection or null if collection is empty.
     */
    public Ticket getFirst() {
        if (ticketsCollection.isEmpty()) return null;
        return ticketsCollection.stream().findFirst().get();
    }

    /**
     * @return The last element of the collection or null if collection is empty.
     */
    public Ticket getLast() {
        if (ticketsCollection.isEmpty()) return null;
        Ticket lastElement = null;
        if (ticketsCollection.size()>0) {
            Iterator<Ticket> iterator = ticketsCollection.iterator();
            while (iterator.hasNext()) {
                lastElement = iterator.next();
            }
        }
        return lastElement;
    }

    /**
     * @param id ID of the ticket.
     * @return A ticket by his ID or null if ticket isn't found.
     */
    public Ticket getById(Long id) {
        for (Ticket ticket : ticketsCollection) {
            if (ticket.getId().equals(id)) return ticket;
        }
        return null;
    }

    /**
     * @return A ticket whose price is the greatest
     */
    public Ticket getGreatestValue() {
        Ticket max = null;
        long max_price = -1;
        for (Ticket ticket: ticketsCollection) {
           if (ticket.getPrice()>max_price) max = ticket;
        }
        return max;
    }
    /**
     * @param ticketToFind A ticket who's value will be found.
     * @return A ticket by his value or null if ticket isn't found.
     */
    public Ticket getByValue(Ticket ticketToFind) {
        for (Ticket ticket : ticketsCollection) {
            if (ticket.equals(ticketToFind)) return ticket;
        }
        return null;
    }

    /**
     * @param personToFind A person who's value will be found.
     * @return A ticket by his value or null if person isn't found.
     */
    public Ticket getByPerson(Person personToFind) {
        for (Ticket ticket: ticketsCollection) {
           if (ticket.getPerson().equals(personToFind)) return ticket;
        }
        return null;
    }

    /**
     * @return Sum of all tickets' price or 0 if collection is empty.
     */
    public double getSumOfPrice() {
        double sumOfPrice = 0;
        for (Ticket ticket : ticketsCollection) {
            sumOfPrice += ticket.getPrice();
        }
        return sumOfPrice;
    }

    /**
     * @return Ticket, who has min by price
     * @throws CollectionIsEmptyException If collection is empty.
     */
    public String minByPrice() throws CollectionIsEmptyException {
        if (ticketsCollection.isEmpty()) throw new CollectionIsEmptyException();

        Ticket minTicket = getFirst();
        for (Ticket ticket : ticketsCollection) {
            if (ticket.getPrice().compareTo(minTicket.getPrice()) < 0) {
                minTicket = ticket;
            }
        }
        return minTicket.toString();
    }

    /**
     * @param price to filter by.
     * @return Information about valid tickets or empty string, if there's no such tickets.
     */
    public String priceFilteredInfo(Long price) {
        String info = "";
        for (Ticket ticket : ticketsCollection) {
            if (ticket.getPrice() - price > 0) {
                info += ticket + "\n\n";
            }
        }
        return info.trim();
    }

    /**
     * Adds a new ticket to collection.
     * @param ticket A ticket to add.
     */
    public void addToCollection(Ticket ticket) {
        ticketsCollection.add(ticket);
    }

    /**
     * Removes a new ticket to collection.
     * @param ticket A ticket to remove.
     */
    public void removeFromCollection(Ticket ticket) {
        ticketsCollection.remove(ticket);
    }

    /**
     * Remove tickets greater than the selected one.
     * @param ticketToCompare A ticket to compare with.
     */
    public void removeGreater(Ticket ticketToCompare) {
        ticketsCollection.removeIf(ticket -> ticket.compareTo(ticketToCompare) > 0);
    }


    /**
     * Remove tickets lower than the selected one.
     * @param ticketToCompare A ticket to compare with.
     */
    public void removeLower(Ticket ticketToCompare) {
        ticketsCollection.removeIf(ticket -> {
            return ticket.compareTo(ticketToCompare) < 0;
        });
    }

    /**
     * Clears the collection.
     */
    public void clearCollection() {
        ticketsCollection.clear();
    }

    /**
     * Generates next ID. It will be (the bigger one + 1).
     * @return Next ID.
     */
    public Long generateNextId() {
        if (ticketsCollection.isEmpty()) return 1L;
        return getLast().getId() + 1;
    }

    /**
     * Saves the collection to file.
     */
    public void saveCollection() {
            fileManager.writeCollection((HashSet<Ticket>) ticketsCollection);
            lastSaveTime = LocalDateTime.now();
    }

    /**
     * Loads the collection from file.
     */
    private void loadCollection() {

        ticketsCollection = (HashSet<Ticket>) fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }


    @Override
    public String toString() {
        if (ticketsCollection.isEmpty()) return "Collection is empty!";

        String info = "";
        for (Ticket ticket : ticketsCollection) {
            info += ticket;
            if (ticket != getLast()) info += "\n\n";
        }
        return info;
    }
}
