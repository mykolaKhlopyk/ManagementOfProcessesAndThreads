package main.java.ua.mykola.lab4.task2;

import java.util.concurrent.locks.ReentrantLock;

public class Monitor2  extends Thread {
    Garden garden;
    ReentrantLock locker;

    public Monitor2(Garden garden, ReentrantLock locker) {
        this.garden = garden;
        this.locker = locker;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Monitor2 start");

            locker.lock();
            System.out.println(garden.toString());
            locker.unlock();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }


}
