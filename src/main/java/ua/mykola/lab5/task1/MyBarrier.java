package main.java.ua.mykola.lab5.task1;

public class MyBarrier {
    private int numberOfParties;
    private int currentNumberOfParties;

    public MyBarrier(int parties) {
        numberOfParties = parties;
        currentNumberOfParties = 0;
    }

    public synchronized void await() throws InterruptedException {
        currentNumberOfParties++;
        if (currentNumberOfParties < numberOfParties) {
            wait();
        }
        currentNumberOfParties = 0;
        notifyAll();

    }
}