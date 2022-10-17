package main.java.ua.mykola.lab2.task2;

public class Storage {
    private int numberOfMunition;
    Storage(int numberOfMunition){
        this.numberOfMunition=numberOfMunition;
    }
    public boolean getItem(){
        if (numberOfMunition>0){
            numberOfMunition--;
            return true;
        }
        return false;
    }
}
