package main.java.ua.mykola.modul.task1;

import java.util.LinkedList;
import java.util.List;

public class BankTeller extends Thread {
    private int cash;
    private static int number = 0;
    private Viewer viewer;
    private List<Client> clients;

    public BankTeller(Viewer viewer) {
        super("BankTeller " + number++);
        clients = new LinkedList<Client>();
        this.viewer = viewer;
        this.cash = 10000;
    }

    public void add(Client client) {
        clients.add(client);
    }


    @Override
    public void run() {
        while (true) {
            if (clients.isEmpty()) {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {

                Client client = clients.remove(0);
                try {
                    client.task.doTaskWithMoney(this);
                    viewer.checkAndDeleteOrAddCash();
                    sleep((int) (3000));
                    System.out.println(client);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public int getNumberOfClients() {
        return clients.size();
    }

    @Override
    public String toString() {
        return clients.toString();
    }

    public int getCash() {
        return cash;
    }

    public void removeCash(int sum) {
        this.cash -= sum;
    }
    public void addCash(int sum) {
        this.cash += sum;
    }

}
