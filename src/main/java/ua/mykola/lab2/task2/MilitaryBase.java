package main.java.ua.mykola.lab2.task2;

public class MilitaryBase {

    public boolean isIvanovBroughtNewItemToPetrov;
    public synchronized void ivanovCameWithItemToPetrov(){

        this.isIvanovBroughtNewItemToPetrov=true;
        notify();
    }

    public synchronized boolean getItem() {
        while (!isIvanovBroughtNewItemToPetrov) {

            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        isIvanovBroughtNewItemToPetrov = false;
        notify();
        return true;
    }


}
