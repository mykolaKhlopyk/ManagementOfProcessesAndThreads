package main.java.ua.mykola.lab4.task1;

import main.java.ua.mykola.lab2.task2.Petrov;

public class Person {
    private String name;
    private String lasTName;

    private String phoneNumber;
    Person(String name, String lasTName, String phoneNumber){
        this.name=name;
        this.lasTName=lasTName;
        this.phoneNumber=phoneNumber;
    }

    @Override
    public String toString() {
        return name+" "+lasTName+ " "+ phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        Person person=(Person) obj;
        return this.lasTName.equals(person.lasTName) && this.name.equals(person.name) && this.phoneNumber.equals(person.phoneNumber);
    }

    public String getName() {
        return name;
    }

    public String getLasTName() {
        return lasTName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
