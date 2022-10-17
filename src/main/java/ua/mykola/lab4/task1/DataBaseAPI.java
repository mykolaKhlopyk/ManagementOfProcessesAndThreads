package main.java.ua.mykola.lab4.task1;

public interface DataBaseAPI {
    String findPhoneByLastName(String lastName);
    String findLastNameByPhoneNumber(String phoneNumber);
    void deleteRecord(Record record);
    void addRecord(Record record);
    void printDataBase();
}
