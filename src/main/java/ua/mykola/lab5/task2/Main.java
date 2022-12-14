package main.java.ua.mykola.lab5.task2;

import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        Checking checking = new Checking();
        CyclicBarrier barrier = new CyclicBarrier(4, checking);

        for (int i = 0; i < 4; i++) {
            String str=createStringABCD();
            System.out.println(str);
            Changer changer=new Changer(str, barrier);
            checking.addChanger(changer);
            Thread thread = new Thread(changer);
            thread.start();
        }
    }
    public static String createStringABCD(){
        int len = 7;
        StringBuilder str=new StringBuilder();
        for (int i = 0; i < len; i++) {
            char randomChar = (char)(((int)(Math.random()*4))+(int)'A');
            str.append(randomChar);
        }
        return str.toString();
    }
}

