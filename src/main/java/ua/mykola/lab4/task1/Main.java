package main.java.ua.mykola.lab4.task1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final String filePath = "file.txt";

    public static void main(String[] args) throws IOException {
        ReentrantLock locker = new ReentrantLock();
        DataBaseAPI dataBaseAPI = new MyDataBase(filePath);
        Person[] persons = new Person[]{
                new Person("Bob", "Keaton", "0935"),
                new Person("Mark", "KKort", "12345"),
                new Person("Curt", "Kit", "4321"),
                new Person("Rob", "Kitty", "1111")
        };
        Writer[] writers = new Writer[]{
                new Writer(dataBaseAPI, locker, WriterTask.ADD, persons[0]),
                new Writer(dataBaseAPI, locker, WriterTask.ADD, persons[1]),
                new Writer(dataBaseAPI, locker, WriterTask.ADD, persons[2]),
                new Writer(dataBaseAPI, locker, WriterTask.ADD, persons[3]),
                new Writer(dataBaseAPI, locker, WriterTask.DELETE, persons[2]),
        };
        Reader[] readers = new Reader[]{
                new Reader(dataBaseAPI, locker, ReaderTask.FIND_PERSON_BY_LAST_NAME, persons[0]),
                new Reader(dataBaseAPI, locker, ReaderTask.FIND_PERSON_BY_LAST_NAME, persons[3]),
                new Reader(dataBaseAPI, locker, ReaderTask.FIND_PERSON_BY_LAST_NAME, persons[2]),
                new Reader(dataBaseAPI, locker, ReaderTask.FIND_PERSON_BY_PHONE, persons[1])
        };
        for (Writer writer : writers) {
            writer.start();
        }
        for (Reader reader : readers) {
            reader.start();
        }
    }
}
