package main.java.ua.mykola.lab4.task2;

import java.util.concurrent.locks.ReentrantLock;

public class Nature extends Thread{
    Garden garden;
    ReentrantLock locker;
    public Nature(Garden garden,  ReentrantLock locker) {
        this.garden = garden;
        this.locker=locker;
    }

    @Override
    public void run() {
        while (true){
            System.out.println("Nature start");

            locker.lock();
            for (int i = 0; i < Garden.size; i++) {
                for (int j = 0; j < Garden.size; j++) {

                        garden.divide(i, j, (int)(Math.random()*2));

                }
            }
            locker.unlock();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
