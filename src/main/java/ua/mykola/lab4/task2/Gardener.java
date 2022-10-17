package main.java.ua.mykola.lab4.task2;

import java.util.concurrent.locks.ReentrantLock;

public class Gardener extends Thread{
    Garden garden;
    ReentrantLock locker;
    public Gardener(Garden garden,  ReentrantLock locker) {
        this.garden = garden;
        this.locker=locker;
    }

    @Override
    public void run() {
        while (true){
            System.out.println("Gardener start");
            locker.lock();
            for (int i = 0; i < Garden.size; i++) {
                for (int j = 0; j < Garden.size; j++) {
                    if (garden.isDried(i, j)){
                        garden.waterPlant(i, j);
                    }
                }
            }
            locker.unlock();
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
