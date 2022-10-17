package main.java.ua.mykola.lab2.task2;

public class Main {
    public static void main(String[] args) {
        MilitaryBase militaryBase = new MilitaryBase();
        Storage storage = new Storage(10);
        Car car = new Car();
        Ivanov ivanov = new Ivanov(storage, militaryBase);
        Petrov petrov = new Petrov(militaryBase, car);
        Nechypurchuk nechypurchuk = new Nechypurchuk(car, petrov);
        ivanov.start();
        petrov.start();
        nechypurchuk.start();
    }
}
