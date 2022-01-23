package ru.spbstu.telematics.java;

import java.util.concurrent.locks.ReentrantLock;

public class Flag {
    ReentrantLock lock = new ReentrantLock();

    boolean tryToRaiseTheFlag() {
        return lock.tryLock();
    }

    void lowerTheFlag() {
        lock.unlock();
    }
}
