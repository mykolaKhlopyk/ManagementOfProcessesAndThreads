package main.java.ua.mykola.lab6.multiplying;

import main.java.ua.mykola.lab6.main.Matrix;
import main.java.ua.mykola.lab6.main.Multiplying;

public class TapeMatrixMultiplying implements Multiplying {
    private Matrix a;
    private Matrix b;
    private Matrix result;
    private  int numberOfThreads;

    public Matrix multiply(Matrix a, Matrix b, int numberOfThreads){
        this.a=a;
        this.b=b;
        result = new Matrix(a.getN(), b.getM());
        this.numberOfThreads = numberOfThreads;
        Thread[] tapes = new Thread[numberOfThreads];
        for(int i = 0; i < tapes.length; i++){
            tapes[i] = new Thread(new Tape(i));
        }
        for(int i = 0; i < tapes.length; i++){
            tapes[i].start();
        }
        for(int i = 0; i < tapes.length; i++){
            try {
                tapes[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private class Tape implements Runnable{
        private final int part_index;
        public Tape(int part_index){
            this.part_index = part_index;
        }

        @Override
        public void run() {

            int pivot = (int) Math.ceil(a.getM() *1.0/  numberOfThreads);
            for (int row = part_index * pivot; row < (part_index + 1) * pivot && row < a.getN(); row++) {
                int counter = 0;
                int index = row;
                while (counter < a.getN()) {
                    int cell = 0;
                    for (int i = 0; i < a.getN(); i++) {
                        cell += a.getValue(row,i) * b.getValue(i, index);
                    }
                    result.setValue(row, index, cell);
                    counter++;
                    index = (index + 1) % a.getN();
                }
            }
        }
    }
}
