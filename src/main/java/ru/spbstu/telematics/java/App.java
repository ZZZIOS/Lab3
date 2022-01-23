package ru.spbstu.telematics.java;

import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new LinkedList<Thread>();
        Flag flag = new Flag();
        int berries_on_field = new Integer(1000);

        Neighbor b = new Neighbor(flag, berries_on_field);

        Neighbor c = new Neighbor(flag, berries_on_field);

        b.setNeighbor(c);
        c.setNeighbor(b);

        Thread t = new Thread(b);
        t.start();
        Thread t1 = new Thread(c);
        t1.start();
        t.join();
        t1.join();
    }
}
