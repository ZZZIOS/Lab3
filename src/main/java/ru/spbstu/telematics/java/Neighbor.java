package ru.spbstu.telematics.java;

import java.util.Random;

public class Neighbor implements Runnable {
    private Flag flag;
    static private int berriesInTheGarden;
    private int berries = 0;
    private Neighbor neighbor;

    public Neighbor(Flag aaa, int bbb) {
        flag = aaa;
        berriesInTheGarden = bbb;
    }

    public boolean checkBerries() {
        return berriesInTheGarden > 0;
    }

    public void run() {
        Random random = new Random();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (flag.tryToRaiseTheFlag()) {
                    if (checkBerries()) {
                        if (this.progress() <= neighbor.progress()) {
                            berries = this.harvest(berries);
                            flag.lowerTheFlag();
                            try {
                                Thread.sleep(random.nextInt(15));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                flag.lowerTheFlag();
                                Thread.sleep(random.nextInt(20));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else{
                        flag.lowerTheFlag();
                        System.out.println(Thread.currentThread().getName() + " собрал " + berries + " ягод");
                        Thread.currentThread().interrupt();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    int progress() {
        return berries;
    }

    void setNeighbor(Neighbor n) {
        neighbor = n;
    }

    public int harvest(int berries) throws InterruptedException {
        int a = berries;
        int b = new Random().nextInt(10);
        if(b <= berriesInTheGarden) {
            berriesInTheGarden -= b;
            a += b;
        }
        else{
            a += berriesInTheGarden;
            berriesInTheGarden = 0;
        }
        // System.out.println(Thread.currentThread().getName() + " : " + a);
        return a;
    }


}
