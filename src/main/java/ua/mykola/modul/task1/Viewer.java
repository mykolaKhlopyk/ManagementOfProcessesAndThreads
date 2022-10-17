package main.java.ua.mykola.modul.task1;

import java.util.ArrayList;
import java.util.List;

public class Viewer extends Thread {
    private static final int MaxLimit = 100000;
    private static final int MinLimit = 1000;
    private int bankVault=10_000_000;
    List<BankTeller> bankTellerList = new ArrayList<>();

    @Override
    public void run() {
        for (BankTeller bankTeller:bankTellerList ) {
            bankTeller.start();
        }
        int i = 0;
        while(true){
            checkAndDeleteOrAddCash();
            try {

                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(i++ % 100== 0){
                for (BankTeller current: bankTellerList) {
                    System.out.println(current.getName()+" cash: "+current.getCash() + current.toString());
                }
            }
        }
    }

    public Viewer() {
    }

    public void add(BankTeller... bankTellers) {
        for (BankTeller bankTeller : bankTellers) {
            bankTellerList.add(bankTeller);
        }
    }

    public void checkAndDeleteOrAddCash(){
        for (BankTeller currentBankTeller: bankTellerList) {
            if (currentBankTeller.getCash()>MaxLimit){
                int rest = currentBankTeller.getCash()-(2*MinLimit);
                currentBankTeller.removeCash(rest);
                this.bankVault+=rest;
            } else if (currentBankTeller.getCash()<MinLimit) {
                currentBankTeller.addCash(2*MinLimit);
                this.bankVault-=2*MinLimit;
            }
        }
    }
    public BankTeller findBankTeller(){
        BankTeller result = bankTellerList.get(0);
        for (BankTeller currentCashRegister : bankTellerList) {
            result=(result.getNumberOfClients()>currentCashRegister.getNumberOfClients())?currentCashRegister:result;
        }
        return result;
    }
}
