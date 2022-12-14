package main.java.ua.mykola.lab6.multiplying;

import main.java.ua.mykola.lab6.main.Matrix;
import main.java.ua.mykola.lab6.main.Multiplying;

public class SequentialMatrixMultiplication implements Multiplying {
    @Override
    public Matrix multiply(Matrix a, Matrix b, int numberOfThreads) {
        Matrix result = new Matrix(a.getN(), b.getM());
        for (int i = 0; i < a.getN(); i++) {
            for (int j = 0; j < b.getM(); j++) {
                for (int k = 0; k < b.getN(); k++) {
                    result.addValue(i, j, a.getValue(i, k) * b.getValue(k, j));
                }
            }
        }
        return result;
    }
}
