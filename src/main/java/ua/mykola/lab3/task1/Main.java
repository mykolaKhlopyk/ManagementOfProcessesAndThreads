package main.java.ua.mykola.lab3.task1;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    public static final int numberOfBees = 4;
    public static final int numberOfPermits = 1;
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(numberOfPermits);
        Pot pot = new Pot();
        Bear pooh = new Bear(pot);
        pot.setBear(pooh);
        for (int i = 0; i < numberOfBees; i++) {
            Bee bee=new Bee(pot, sem);
            bee.start();
        }
    }
}
