package main.java.ua.mykola.lab5.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Recruits implements Runnable {

    private final int[] recruits;
    private final MyBarrier myBarrier;

    private final int recruitsIndex;
    private final int leftIndex;
    private final int rightIndex;
    private static Boolean finished = false;
    private static final List<Boolean> finishedPart = new ArrayList<>();

    public Recruits(int[] recruits, MyBarrier myBarrier, int recruitsIndex, int leftIndex, int rightIndex) {
        this.recruits = recruits;
        this.myBarrier = myBarrier;
        this.recruitsIndex = recruitsIndex;
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
    }

    public void run() {
        while (!finished.booleanValue()) {
            boolean currentRecruitsFinished = finishedPart.get(recruitsIndex);
            if (!currentRecruitsFinished) {

                    System.out.println(this);

                boolean isUnchanging = true;
                if (leftIndex>0){
                    if (recruits[leftIndex-1] != recruits[leftIndex] && recruits[leftIndex-1]==1 ) {
                        recruits[leftIndex-1] = 0;
                        recruits[leftIndex] = 1;
                        isUnchanging = false;
                    }
                }
                for (int i = leftIndex; i < rightIndex - 1; i++) {
                    if (recruits[i] != recruits[i + 1] && recruits[i]==1 ) {
                        recruits[i] = 0;
                        recruits[i + 1] = 1;
                        i++;
                        isUnchanging = false;
                    }
                }
                if (isUnchanging) {
                    finish();
                }
            }
            try {
                myBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void finish() {
        System.out.println("Part " + recruitsIndex + " finished");
        finishedPart.set(recruitsIndex, true);
        for (boolean part : finishedPart) {
            if (!part) {
                return;
            }
        }
        finished = true;
    }

    public static void fillFinishedArray(int numberOfParts) {
        for (int i = 0; i < numberOfParts; i++) {
            finishedPart.add(false);
        }
    }

    @Override
    public String toString() {
        return "(Part " + recruitsIndex + ") " + Arrays.toString(recruits);
    }


}

