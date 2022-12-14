package main.java.ua.mykola.lab6.main;

import main.java.ua.mykola.lab6.multiplying.CannonsMethodMatrixMultiplying;
import main.java.ua.mykola.lab6.multiplying.FoxesMethodForMatrixMultiplying;
import main.java.ua.mykola.lab6.multiplying.SequentialMatrixMultiplication;
import main.java.ua.mykola.lab6.multiplying.TapeMatrixMultiplying;

public class Main {
    public static void main(String[] args) {
        Matrix a = new Matrix(30);
        Matrix b = new Matrix(30);
        a.setRandomValuesInMatrix(11);
        b.setRandomValuesInMatrix(11);
        Multiplying[] multiplyings = new Multiplying[]{
                new SequentialMatrixMultiplication(),
                new TapeMatrixMultiplying(),
                new FoxesMethodForMatrixMultiplying(),
                new CannonsMethodMatrixMultiplying()
        };
         System.out.println(a + "\n");
        System.out.println(b + "\n");
        for (Multiplying multiplying : multiplyings) {
            System.out.println(multiplying.multiply(a,b,4));
        }
    }

}
