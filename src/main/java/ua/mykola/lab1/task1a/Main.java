package main.java.ua.mykola.lab1.task1a;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {



        JFrame f = new JFrame();
        JSlider slider = new JSlider(0, 100, 50);
        JButton buttonStart = new JButton("click");
        SpinnerModel value1 = new SpinnerNumberModel(0, 0, 5, 1);
        SpinnerModel value2 = new SpinnerNumberModel(0, 0, 5, 1);
        JSpinner increment = new JSpinner(value1);
        JSpinner decrement = new JSpinner(value2);





        JThread thread1 = new JThread(increment, slider, SIGN.PLUS);
        JThread thread2 = new JThread(decrement, slider, SIGN.MINUS);

        slider.setBounds(50, 100, 250, 60);
        increment.setBounds(50, 200, 100, 40);
        decrement.setBounds(200, 200, 100, 40);
        buttonStart.setBounds(50, 300, 250, 40);

        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(10);

        buttonStart.addActionListener(e -> {
            thread1.start();
            thread2.start();
            buttonStart.setEnabled(false);
        });



        f.add(increment);
        f.add(decrement);
        f.add(buttonStart);
        f.add(slider);

        f.setSize(360, 500);
        f.setLayout(null);
        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

