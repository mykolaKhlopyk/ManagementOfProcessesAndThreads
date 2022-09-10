package main.java.ua.mykola.task1a;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(Math.sqrt(2));
        System.out.println(Math.sqrt(2.01));
        //створюємо необхідні обєкти для  swing
        JFrame f = new JFrame();
        JSlider slider = new JSlider(0, 100, 50);
        JButton buttonStart = new JButton("click");
        SpinnerModel value1 = new SpinnerNumberModel(0, 0, 5, 1);
        SpinnerModel value2 = new SpinnerNumberModel(0, 0, 5, 1);
        JSpinner increment = new JSpinner(value1);
        JSpinner decrement = new JSpinner(value2);




        //потоки
        JThread thread1 = new JThread(increment, slider, SIGN.PLUS);
        JThread thread2 = new JThread(decrement, slider, SIGN.MINUS);

        //задаємо розміри
        slider.setBounds(50, 100, 250, 60);
        increment.setBounds(50, 200, 100, 40);
        decrement.setBounds(200, 200, 100, 40);
        buttonStart.setBounds(50, 300, 250, 40);

        // неважливо (задаємо вигляд слайдеру)
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(10);

        //кнопка початку
        buttonStart.addActionListener(e -> {
            thread1.start();
            thread2.start();
            buttonStart.setEnabled(false);
        });



        //заповнюєм фрейм
        f.add(increment);
        f.add(decrement);
        f.add(buttonStart);
        f.add(slider);

        f.setSize(360, 500);
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

