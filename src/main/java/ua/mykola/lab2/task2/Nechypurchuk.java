package main.java.ua.mykola.lab2.task2;

public class Nechypurchuk extends Thread{
    Car car;
    Petrov petrov;
    Nechypurchuk(Car car, Petrov petrov){
        this.car=car;
        this.petrov=petrov;
    }
    @Override
    public void run() {
        while (!petrov.hasFinished()){
            car.waitForNewItem();
            System.out.println("Nechypurchuk counted "+car.getNumberOfItems()+" items\n");
        }
    }
}
