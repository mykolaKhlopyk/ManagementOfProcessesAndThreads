package main.java.ua.mykola.ex.rmi;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientRMITask8 {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        Scanner scanner = new Scanner(System.in);
        MyRMIServer rmiServer = (MyRMIServer) Naming.lookup("rmi://localhost/server");
        System.out.println("MENU\n"
                + "1 - cars of entered brand\n"
                + "2 - cars of special year, whose price is more than entered\n"
                + "3 - cars by using time(time in years)"
                );
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter brand");
                String brand = scanner.next();
                System.out.println(rmiServer.getCarOfSpecialBrand(brand));
                break;
            case 2:
                System.out.print("Enter year");
                int year = scanner.nextInt();
                System.out.print("Enter value for price");
                int value = scanner.nextInt();
                System.out.println(rmiServer.getListOfCarsOfCustomYearWhichPriceIsMoreThanValue(year, value));
                break;
            case 3:
                System.out.print("Enter n(time of using in year)");
                int n = scanner.nextInt();
                System.out.println(rmiServer.getListOfCarsWhichAreUsedMoreThanNYears(n));
                break;
        }

    }
}
