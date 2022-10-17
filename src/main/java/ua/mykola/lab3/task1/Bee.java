package main.java.ua.mykola.lab3.task1;

import java.util.concurrent.Semaphore;

public class Bee extends Thread {
    private Pot pot;
    private Semaphore sem;
    private static int numberOfBees = 0;
    private static final int numberOfHoney = 3;
    private static final int timeForSleeping = 300;
    private int beeNumber;
    Bee(Pot pot, Semaphore sem) {
        this.sem = sem;
        this.pot = pot;
        beeNumber=numberOfBees++;
    }

    @Override
    public void run() {
        while (!pot.isFull) {
            try {
                sem.acquire();
                if (!pot.isFull) {
                    pot.putHoneyInPot(numberOfHoney, beeNumber);
                }
                sem.release();
                sleep(timeForSleeping);
            }catch (InterruptedException e){
                System.out.println("Error in putting honey");
            }
        }
    }




}
