package main.java.ua.mykola.lab3.task2;

public class Customer extends Thread {
    Hairdresser hairdresser;

    Customer(Hairdresser hairdresser, int number) {
        super(number+" customer");
        this.hairdresser = hairdresser;
    }

    public synchronized void come() {
        System.out.println(getName() + " come to barber shop");
        hairdresser.stayInQueue(this);
        if (hairdresser.isReversed) {
            System.out.println(getName() + " starts sleep");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            wakeHairdresserUp();
        }
    }

    @Override
    public void run() {
        come();
    }

    public  void wakeHairdresserUp() {
        System.out.println(getName() + " wakes hairdresser up");
        synchronized (hairdresser) {
            hairdresser.notify();
        }
    }
}
