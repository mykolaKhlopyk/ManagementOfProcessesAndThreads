package main.java.ua.mykola.modul.task1;

public class Client{
    static int number=0;
    private String name;
    public Task task;
 //   private BankTeller bankTeller;
    Client( Task task){
        this.name = (number++)+" client" ;
       this.task=task;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
