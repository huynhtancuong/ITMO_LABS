package itmo.test;

import itmo.Data.Ticket;

import java.util.HashSet;

public class HashSetExample {
    public static void main(String[] args) {
        HashSet<Ticket> set = new HashSet<Ticket>();

        Ticket t1 = new Ticket();
        Ticket t2 = new Ticket();
        Ticket t3 = new Ticket();

        set.add(t1);
        set.add(t2);
        set.add(t3);

        System.out.println(set);
    }
}
