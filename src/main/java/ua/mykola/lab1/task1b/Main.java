package main.java.ua.mykola.lab1.task1b;

import main.java.ua.mykola.lab1.task1a.JThread;
import main.java.ua.mykola.lab1.task1a.SIGN;

import javax.swing.*;

public class Main {

    private static final int incrementThreadNumber = 1;
    private static final int decrementThreadNumber = 2;
    private static JThread incrementThread;
    private static JThread decrementThread;
    public static void main(String[] args) {
        JSemaphore mySemaphore=new JSemaphore();


        JFrame f = new JFrame();
        JSlider slider = new JSlider(0, 100, 50);
        JButton buttonStart1 = new JButton("Start1");
        JButton buttonStart2 = new JButton("Start2");
        JButton buttonStop1 = new JButton("Stop1");
        JButton buttonStop2 = new JButton("Stop2");

        SpinnerModel value1 = new SpinnerNumberModel(0, 0, 5, 1);
        SpinnerModel value2 = new SpinnerNumberModel(0, 0, 5, 1);
        JSpinner increment = new JSpinner(value1);
        JSpinner decrement = new JSpinner(value2);

        slider.setBounds(50, 100, 250, 60);
        increment.setBounds(50, 200, 100, 40);
        decrement.setBounds(200, 200, 100, 40);
        buttonStart1.setBounds(50, 300, 100, 40);
        buttonStart2.setBounds(200, 300, 100, 40);
        buttonStop1.setBounds(50, 400, 100, 40);
        buttonStop2.setBounds(200, 400, 100, 40);buttonStart1.setBounds(50, 300, 100, 40);

        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(50);

        buttonStart1.addActionListener(event->{
            increment.setValue(0);
            if (mySemaphore.setThread(incrementThreadNumber)){
                incrementThread = new JThread(increment, slider, SIGN.PLUS, mySemaphore);
                incrementThread.start();
            }else{
                JOptionPane.showMessageDialog(null, "slider is reversed");
            }
        });
        buttonStart2.addActionListener(event->{
            decrement.setValue(5); // maxValueOfSpinner
            if (mySemaphore.setThread(decrementThreadNumber)){
                decrementThread = new JThread(decrement, slider, SIGN.MINUS, mySemaphore);
                decrementThread.start();
            }else{
                JOptionPane.showMessageDialog(null, "slider is reversed");
            }
        });
        buttonStop1.addActionListener(event->{
            if (mySemaphore.getThreadNumber()==incrementThreadNumber){

                try{

                   incrementThread.disable();
                }catch(Exception e){
                    System.out.println("Thread has been interrupted");
                }
            }
        });
        buttonStop2.addActionListener(event->{
            if (mySemaphore.getThreadNumber()==decrementThreadNumber){
                try{
                    decrementThread.disable();
                }catch(Exception e){
                    System.out.println("Thread has been interrupted");
                }
            }
        });
        f.add(increment);
        f.add(decrement);
        f.add(buttonStart1);
        f.add(buttonStart2);
        f.add(buttonStop1);
        f.add(buttonStop2);
        f.add(slider);

        f.setSize(360, 500);
        f.setLayout(null);
        f.setVisible(true);
    }
}
