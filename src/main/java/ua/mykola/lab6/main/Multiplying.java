package main.java.ua.mykola.lab6.main;

import main.java.ua.mykola.lab6.main.Matrix;

public interface Multiplying {
    Matrix multiply(Matrix a, Matrix b, int numberOfThreads);
}
