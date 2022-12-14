package main.java.ua.mykola.lab6.multiplying;

import main.java.ua.mykola.lab6.main.Matrix;
import main.java.ua.mykola.lab6.main.Multiplying;

public class FoxesMethodForMatrixMultiplying implements Multiplying {
    private Matrix a;
    private Matrix b;
    private Matrix result;
    private  int numberOfThreads;
    @Override
    public Matrix multiply(Matrix a, Matrix b, int numberOfThreads){
        this.a=a;
        this.b=b;
        result = new Matrix(a.getN(), b.getM());
        this.numberOfThreads = numberOfThreads;
        Thread[] tapes = new Thread[numberOfThreads];
        for(int i = 0; i < tapes.length; i++){
            tapes[i] = new Thread(new FoxPart(i));
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

    private class FoxPart implements Runnable{

        private final int part_index;
        public FoxPart(int part_index){
            this.part_index = part_index;
        }

        @Override
        public void run() {
            int pivot = (int) Math.ceil(a.getM() *1.0/  numberOfThreads);
             for (int row = part_index * pivot; row < (part_index + 1) * pivot && row < a.getM(); row++) {
                int b_i = row;
                int a_j = row;
                for (int counter = 0; counter < a.getM(); counter++) {
                    for (int i = 0; i < a.getM(); i++) {
                        result.addValue(row, i, (a.getValue(row, a_j)*b.getValue(b_i, i)));
                    }

                    a_j = (a_j + 1) % a.getN();
                    b_i = (b_i + 1) % b.getN();
                }
            }
        }
    }
}
