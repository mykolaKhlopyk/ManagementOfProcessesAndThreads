package main.java.ua.mykola.ex.rmi;

import main.java.ua.mykola.ex.Car;

import java.rmi.Remote;
import java.util.List;

interface MyRMIServer extends Remote {
    List<Car> getCarOfSpecialBrand(String brand);
    List<Car> getListOfCarsWhichAreUsedMoreThanNYears(int n);
    List<Car> getListOfCarsOfCustomYearWhichPriceIsMoreThanValue(int year, int value);

}