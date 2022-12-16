package main.java.ua.mykola.ex;

import java.io.Serializable;
import java.util.Comparator;

public class Car implements Serializable, Comparator<Car>, Comparable<Car> {
    private int id;
    private static int counterId=0;



    private String brand;
    private String model;
    private int year;
    private int price;
    private int numberCode;

    public Car(String brand, String model, int year, int price, int numberCode) {
        this.id = counterId++;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.numberCode = numberCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberCode() {
        return numberCode;
    }

    public void setNumberCode(int numberCode) {
        this.numberCode = numberCode;
    }

    @Override
    public int compare(Car o1, Car o2) {
        return o1.id-o2.id;
    }
    @Override
    public int compareTo(Car o) {
        return this.id=o.getId();
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", numberCode=" + numberCode +
                '}';
    }
}
