package main.java.ua.mykola.lab2.task2;

public class Car {

    private int numberOfItems;
    private boolean isNumberOfItemsChanged;

    Car(){
        numberOfItems=0;
        isNumberOfItemsChanged=false;
    }
    public synchronized void putIn(){
        this.numberOfItems++;
        isNumberOfItemsChanged=true;
        notify();
    }
    public int getNumberOfItems(){
        return numberOfItems;
    }
    public synchronized void waitForNewItem(){
        while(!isNumberOfItemsChanged){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        isNumberOfItemsChanged=false;
        notify();
    }
}
