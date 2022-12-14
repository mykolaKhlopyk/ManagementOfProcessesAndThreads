package main.java.ua.mykola.lab5.task1;
import java.util.Arrays;

public class Main {
    private static final int SIZE = 100;
    private static final int NUMBER_OF_THREADS = 2;
    private static final int NUMBER_OF_RECRUITS_IN_THREAD = 50;

    private static final Thread[] threads = new Thread[NUMBER_OF_THREADS];
    private static final int[] recruits = new int[SIZE];
    private static final MyBarrier MY_BARRIER = new MyBarrier(NUMBER_OF_THREADS);

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            recruits[i]=(int) (Math.random()*2);
        }
        //1-right
        //0-left
        Recruits.fillFinishedArray(NUMBER_OF_THREADS);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Recruits(recruits, MY_BARRIER, i, i * NUMBER_OF_RECRUITS_IN_THREAD, (i + 1) * NUMBER_OF_RECRUITS_IN_THREAD));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Result: " + Arrays.toString(recruits));
    }


}
