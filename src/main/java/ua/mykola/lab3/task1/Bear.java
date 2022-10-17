package main.java.ua.mykola.lab3.task1;

import java.util.concurrent.Semaphore;

public class Bear extends Thread{
    private Pot pot;
    private static final int timeEating = 400;
    Bear(Pot pot){
        this.pot=pot;
    }
    @Override
    public void run() {
        while(pot.isFull){
            pot.getHoney();
            try {
                sleep(500);
            }catch (InterruptedException e){

            }
        }
    }

}
