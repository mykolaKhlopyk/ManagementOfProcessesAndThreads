package main.java.ua.mykola.task1a;

import main.java.ua.mykola.task1b.JSemaphore;

import javax.swing.*;

public class JThread extends Thread {
    private final static int koef = 1;
    private SIGN sign;
    private JSpinner priority;
    private JSlider mainJSlider;
    private boolean isActive;
    private JSemaphore jSemaphore;
    public JThread(JSpinner priority, JSlider mainJSlider, SIGN sign) {
        this.priority = priority;
        this.mainJSlider = mainJSlider;
        this.sign = sign;
        this.isActive=false;
    }
    public JThread(JSpinner priority, JSlider mainJSlider, SIGN sign, JSemaphore jSemaphore) {
        this(priority, mainJSlider, sign);
        this.jSemaphore=jSemaphore;
    }
    public void disable(){
        isActive=false;
        jSemaphore.clear();
    }
    public void run() {
        isActive = true;
        while (isActive) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
            }
            synchronized (mainJSlider) {
                int currentValue=Integer.valueOf(priority.getValue().toString());
                int newSliderPosition = mainJSlider.getValue() + koef * (Integer.valueOf(priority.getValue().toString())) * ((sign == SIGN.MINUS) ? -1 : 1);

                newSliderPosition=newSliderPosition>90 ? 90 : newSliderPosition;
                newSliderPosition=newSliderPosition<10 ? 10 : newSliderPosition;
                mainJSlider.setValue(newSliderPosition);

            }
        }
    }
}