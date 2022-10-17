package main.java.ua.mykola.lab3.task2;

import java.util.ArrayDeque;
import java.util.Queue;

public class Hairdresser extends Thread {
    boolean isReversed = false;
    private Queue<Customer> customers = new ArrayDeque<>();
    private static final int timeOfMakingHaircut = 5000;
    @Override
    public void run() {
        while (true) {
            if (customers.isEmpty()) {
                isReversed = false;
                System.out.println("Hairdresser goes sleep");
                sleepInBarberShop();
                System.out.println("Hairdresser wakes up");
            }

            isReversed = true;
            makeHaircut(customers.poll());

        }
    }

    public synchronized void stayInQueue(Customer customer) {
        customers.add(customer);
    }

    public void makeHaircut(Customer customer) {
        try {
            System.out.println("Start haircut for " + customer.getName());
            sleep(timeOfMakingHaircut);
            System.out.println("Finish haircut for " + customer.getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected synchronized void sleepInBarberShop(){
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
