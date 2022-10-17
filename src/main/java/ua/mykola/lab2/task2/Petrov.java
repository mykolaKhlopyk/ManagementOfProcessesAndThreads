package main.java.ua.mykola.lab2.task2;

public class Petrov extends Thread {
    private MilitaryBase militaryBase;
    private Car car;
    private boolean isItemBrought;
    private static final int timeForPackingItemInCar = 1000;
    private boolean isFinished;

    Petrov(MilitaryBase militaryBase, Car car) {
        this.militaryBase = militaryBase;
        this.car = car;
    }

    @Override
    public void run() {
        while (militaryBase.getItem()) {
            System.out.println("Petrov have gotten new item and packs it in car");
            packInCar();
        }
        isFinished = true;
    }

    private void packInCar() {
        try {
            Thread.sleep(timeForPackingItemInCar);
            car.putIn();
        } catch (InterruptedException e) {
        }
    }

    public boolean hasFinished() {
        return isFinished;
    }

}