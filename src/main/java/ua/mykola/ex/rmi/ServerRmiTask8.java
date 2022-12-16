package main.java.ua.mykola.ex.rmi;

import main.java.ua.mykola.ex.Car;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class ServerRmiTask8 {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(123);
        MyRMIServer service = new Service();
        registry.rebind("exam", service);
        System.out.println("Server started!");
    }

    static class Service extends UnicastRemoteObject implements MyRMIServer {
        private List<Car> cars;
        public Service() throws RemoteException {
            super();
            cars = new ArrayList<>();
            cars.add(new Car("Honda", "A4", 1900,
                    300_000, 12241243));
            cars.add(new Car("Skoda", "A5", 2000,
                    160_000, 12345244));
            cars.add(new Car("Audi", "B4", 2003,
                    100_000, 42541255));
            cars.add(new Car("Mercedes", "B1", 2002,
                    350_000, 11111334));
            cars.add(new Car("BMW", "C0", 2012,
                    800_000, 82202386));

        }

        @Override
        public List<Car> getCarOfSpecialBrand(String brand) {
            return cars.stream().filter(t->t.getBrand().equals(brand)).collect(Collectors.toList());
        }

        @Override
        public List<Car> getListOfCarsWhichAreUsedMoreThanNYears(int n) {
            return cars.stream().filter(t->t.getYear()>n).collect(Collectors.toList());
        }

        @Override
        public List<Car> getListOfCarsOfCustomYearWhichPriceIsMoreThanValue(int year, int value) {
            return cars.stream().filter(t->t.getYear()==year && t.getPrice()>value).collect(Collectors.toList());
        }
    }
}
