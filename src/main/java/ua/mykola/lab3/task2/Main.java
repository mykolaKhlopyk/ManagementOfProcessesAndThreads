package main.java.ua.mykola.lab3.task2;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {

        Hairdresser hairdresser = new Hairdresser();
        hairdresser.start();
        int i=0;
        while (true){
            (new Customer(hairdresser, i++)).start();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}


