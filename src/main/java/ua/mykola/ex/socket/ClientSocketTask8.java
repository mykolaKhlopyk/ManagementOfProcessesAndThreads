package main.java.ua.mykola.ex.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import main.java.ua.mykola.ex.Car;

public class ClientSocketTask8 {

    private static final int port = 1000;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("MENU\n"
                    + "1 - cars of entered brand\n"
                    + "2 - cars of special year, whose price is more than entered\n"
                    + "3 - cars by using time(time in years)\n"
                    + "4 - exit");
            socket = new Socket("localhost", port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            int command = scanner.nextInt();
            if (command == 4)
            {
                socket = new Socket(host.getHostName(), port);
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeInt(command); oos.flush();
                break;
            }
            String message="";
            switch (command) {
                case 1:
                    System.out.println("Enter brand");
                    String brand = scanner.next();
                    message = command + "_" + brand;
                    break;
                case 2:
                    System.out.print("Enter year");
                    int year = scanner.nextInt();
                    System.out.print("Enter value for price");
                    int value = scanner.nextInt();
                    message = command + "_" + year + "_" + value;
                    break;
                case 3:
                    System.out.print("Enter n(time of using in year)");
                    int n = scanner.nextInt();
                    message = command + "_" + n;
                    break;

            }
            oos.writeBytes(message);
            oos.flush();
            System.out.println("Output: ");
            ois = new ObjectInputStream(socket.getInputStream());
            ((ArrayList<Car>) ois.readObject()).stream().forEach(System.out::println);

            ois.close();
            oos.close();
            Thread.sleep(100);
        }
        oos.writeInt(3);
    }
}
