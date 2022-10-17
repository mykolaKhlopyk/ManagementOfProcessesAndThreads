package main.java.ua.mykola.lab4.task2;

import java.io.*;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor1 extends Thread {
    Garden garden;
    ReentrantLock locker;
    String filePath;

    public Monitor1(Garden garden, ReentrantLock locker, String filePath) {
        this.garden = garden;
        this.locker = locker;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Monitor1 start");

            locker.lock();
            File file=new File(filePath);
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
              bufferedWriter.write(garden.toString());
                bufferedWriter.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            locker.unlock();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }


}
