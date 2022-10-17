package main.java.ua.mykola.lab2.task2;

public class Ivanov extends Thread {
    private Storage storage;
    MilitaryBase militaryBase;
    private static final int timeForCarryingItem = 1000;
    private static final int timeForReturningToStorage = 500;
    private boolean hasFinished;

    Ivanov(Storage storage, MilitaryBase militaryBase) {
        this.storage = storage;
        this.militaryBase = militaryBase;
    }

    @Override
    public void run() {
        while (true) {
            if (storage.getItem()) {
                System.out.println("Ivanov took item and carry it to Petrov");
                goToPetrovWithItem();
                System.out.println("Ivanov came to Petrov");
                militaryBase.ivanovCameWithItemToPetrov();
                while (militaryBase.isIvanovBroughtNewItemToPetrov){

                }
                System.out.println("Ivanov moves to new item");
                goToStorage();
            } else {
                break;
            }
        }
        hasFinished = true;
    }

    private void goToPetrovWithItem() {
        try {
            Thread.sleep(timeForCarryingItem);
        } catch (InterruptedException e) {
        }
    }

    private void goToStorage() {
        try {
            Thread.sleep(timeForReturningToStorage);
        } catch (InterruptedException e) {
        }
    }
}