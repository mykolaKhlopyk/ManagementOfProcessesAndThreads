package main.java.ua.mykola.lab4.task1;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.util.concurrent.locks.ReentrantLock;

public class Reader extends Thread {
    DataBaseAPI dataBase;
    ReentrantLock locker;
    ReaderTask task;
    Person person;

    public Reader(DataBaseAPI dataBase, ReentrantLock locker, ReaderTask task, Person person) {
        this.dataBase = dataBase;
        this.locker = locker;
        this.task = task;
        this.person = person;
    }

    @Override
    public void run() {
        locker.lock();
        String result = null;
        switch (task) {
            case FIND_PERSON_BY_PHONE:
                result = dataBase.findPhoneByLastName(person.getLasTName());
                break;
            case FIND_PERSON_BY_LAST_NAME:
                result = dataBase.findLastNameByPhoneNumber(person.getPhoneNumber());
                break;

        }
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        locker.unlock();
        if (result == null) {
            System.out.println("Person is missing");
        } else {
            System.out.println(result);
        }
    }
}
