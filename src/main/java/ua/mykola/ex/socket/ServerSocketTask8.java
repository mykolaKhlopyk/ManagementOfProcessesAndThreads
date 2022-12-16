package main.java.ua.mykola.ex.socket;

import main.java.ua.mykola.ex.Car;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Callback
{
    public boolean shouldEnd = false;
}

class CustomerIterator implements Runnable
{
    private Socket socket;
    private Callback callback;
    private List<Car> cars;

    public CustomerIterator(Socket socket, Callback callback, List<Car> cars)
    {
        this.callback = callback;
        this.socket = socket;
        this.cars = cars;
    }

    @Override
    public void run() {
        try {
            InputStreamReader ois = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(ois);
            String message = reader.readLine();
            String splitMessage[] = message.split("_");
            String commandIndex = splitMessage[0];
            System.out.println("Command " + splitMessage[0]);

            if (commandIndex.equals("4"))
            {
                System.out.println("Close command");
                callback.shouldEnd = true;
                return;
            }
            List<Car> result = new ArrayList<>();
            switch (commandIndex) {
                case "1":
                    String brand = String.valueOf(splitMessage[1]);
                    result = getCarOfSpecialBrand(brand);
                    break;

                case "2":
                    int year = Integer.parseInt(splitMessage[1]);
                    int value = Integer.parseInt(splitMessage[2]);
                    result = getListOfCarsOfCustomYearWhichPriceIsMoreThanValue(year, value);
                    break;
                case "3":
                    int n = Integer.parseInt(splitMessage[1]);
                    result = getListOfCarsWhichAreUsedMoreThanNYears(n);
                    break;

            }
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(result);
            ois.close();
            oos.close();
            socket.close();
        }
        catch (IOException e) { }
    }
    public List<Car> getCarOfSpecialBrand(String brand) {
        return cars.stream().filter(t->t.getBrand().equals(brand)).collect(Collectors.toList());
    }
    public List<Car> getListOfCarsWhichAreUsedMoreThanNYears(int n) {
        return cars.stream().filter(t->t.getYear()>n).collect(Collectors.toList());
    }
    public List<Car> getListOfCarsOfCustomYearWhichPriceIsMoreThanValue(int year, int value) {
        return cars.stream().filter(t->t.getYear()==year && t.getPrice()>value).collect(Collectors.toList());
    }
}

public class ServerSocketTask8 {
    private static ServerSocket server;

    private static int port = 9876;

    public static Callback c = new Callback();

    public static void main(String args[]) throws IOException, ClassNotFoundException{
        server = new ServerSocket(port);
        List cars = new ArrayList<>();
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


        while(!c.shouldEnd){
            System.out.println("Waiting");
            Socket socket = server.accept();
            CustomerIterator iterator = new CustomerIterator(socket, c, cars);
            iterator.run();
        }

        server.close();
    }
}
