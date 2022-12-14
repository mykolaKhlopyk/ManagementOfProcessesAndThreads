package main.java.ua.mykola.modul.task1;

//Банк. Имеется банк с кассирами, клиентами и их счетами. Клиент может
//        снимать/пополнять/переводить/оплачивать/обменивать денежные средства. Кассир последовательно обслуживает клиентов. Поток-наблюдатель
//        следит, чтобы в кассах всегда были наличные, при скоплении денег более
//        определенной суммы, часть их переводится в хранилище, при истощении
//        запасов наличных происходит пополнение из хранилища.

public class Main {
    public static void main(String[] args) {
        Task addMoney = b -> {
            b.addCash(5000);
        };
        Task takeMoney = b -> {
            b.removeCash(5000);
        };
        Task transferMoney = b -> {
        };
        Task pay = b -> {
            b.removeCash(1500);
        };
        Viewer viewer = new Viewer();
        BankTeller bankTellerFirst = new BankTeller(viewer);
        BankTeller bankTellerSecond = new BankTeller(viewer);
        viewer.add(bankTellerFirst, bankTellerSecond);
        viewer.start();
        Client[] clients = {
                new Client(addMoney),
                new Client(takeMoney),
                new Client(transferMoney),
                new Client(pay),
                new Client(takeMoney),
                new Client(takeMoney),
                new Client(takeMoney),
                new Client(takeMoney),
                new Client(takeMoney)
        };
        for (Client current:clients) {
            viewer.findBankTeller().add(current);
        }
    }

}
