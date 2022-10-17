package main.java.ua.mykola.lab4.task2;

import java.io.File;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final String filePath = "garden.txt";


    public static void main(String[] args) {
        Garden garden=new Garden();
        ReentrantLock locker = new ReentrantLock();
        Gardener gardener=new Gardener(garden, locker);
        Nature nature=new Nature(garden, locker);
        Monitor1 monitor1=new Monitor1(garden, locker, filePath);
        Monitor2 monitor2=new Monitor2(garden, locker);
        gardener.start();
        nature.start();
        monitor2.start();
        monitor1.start();
    }
}
