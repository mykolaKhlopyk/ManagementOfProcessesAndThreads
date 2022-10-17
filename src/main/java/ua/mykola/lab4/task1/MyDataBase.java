package main.java.ua.mykola.lab4.task1;

import java.io.*;

public class MyDataBase implements DataBaseAPI {
    private final String  filePath;
    MyDataBase(String file) {
        this.filePath = file;
    }

    @Override
    public String findPhoneByLastName(String lastName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String strRecord;
            while ((strRecord = bufferedReader.readLine()) != null) {
                if (strRecord.trim().split(" ")[1].equals(lastName)) {
                    bufferedReader.close();
                    return strRecord.trim().split(" ")[2];
                }
            }
            bufferedReader.close();

        } catch (IOException e) {

        }
        return null;
    }

    @Override
    public String findLastNameByPhoneNumber(String phoneNumber) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String strRecord;
            while ((strRecord = bufferedReader.readLine()) != null) {
                if (strRecord.trim().split(" ")[2].equals(phoneNumber)) {
                    bufferedReader.close();
                    return strRecord.trim().split(" ")[1];
                }
            }
            bufferedReader.close();

        } catch (IOException e) {

        }
        return null;
    }

    @Override
    public void deleteRecord(Record record) {
        File tempFile = new File("myTempFile.txt");
        File file = new File(filePath);

        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String strRecord;

            while ((strRecord = bufferedReader.readLine()) != null) {
                if (!record.toString().equals(strRecord.trim())) {
                    bufferedWriter.write(strRecord+"\n");
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
            file.delete();
            File dump= new File(filePath);
            tempFile.renameTo(dump);
        } catch (IOException e) {

        }
    }

    @Override
    public void addRecord(Record record) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            bufferedWriter.write(record.toString()+"\n");
            bufferedWriter.close();
        } catch (IOException e) {

        }
    }

    public void printDataBase() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String strRecord;
            while ((strRecord = bufferedReader.readLine()) != null) {
                System.out.println(strRecord);
            }
            bufferedReader.close();
        } catch (IOException e) {

        }

    }

}

class Record<T> {
    T data;

    Record(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return this.data.equals((T) obj);
    }
}