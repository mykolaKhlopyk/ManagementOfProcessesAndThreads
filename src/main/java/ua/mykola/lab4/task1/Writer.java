package main.java.ua.mykola.lab4.task1;

import java.util.concurrent.locks.ReentrantLock;

public class Writer extends Thread{
    DataBaseAPI dataBase;
    ReentrantLock locker;
    WriterTask task;
    Person person;

    public Writer(DataBaseAPI dataBase, ReentrantLock locker, WriterTask task, Person person) {
        this.dataBase = dataBase;
        this.locker = locker;
        this.task = task;
        this.person = person;
    }

    @Override
    public void run() {
        locker.lock();
        switch (task) {
            case ADD:
                dataBase.addRecord(new Record<Person>(person));
                break;
            case DELETE:
                dataBase.deleteRecord(new Record<Person>(person));
                break;
        }
        try {
            sleep(  1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        locker.unlock();
        switch (task) {
            case ADD:
                System.out.println("Adding was successful");
                break;
            case DELETE:
                System.out.println("Deleting was successful");
                break;
        }
    }
}
