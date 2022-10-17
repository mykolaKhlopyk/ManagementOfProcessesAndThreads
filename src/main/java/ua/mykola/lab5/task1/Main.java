package main.java.ua.mykola.lab5.task1;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        Concatenation concatenation= new Concatenation();

        SoldiersRaw soldiersRaw = new SoldiersRaw();
        Turn turn1 = new Turn(0, 49, soldiersRaw);
        Turn turn2 = new Turn(50, 99, soldiersRaw);
        Turn turn3 = new Turn(100, 149, soldiersRaw);
        while (true){
            turn1.start();
            turn2.start();
            turn3.start();
        }
    }

}
