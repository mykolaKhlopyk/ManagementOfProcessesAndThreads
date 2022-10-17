package main.java.ua.mykola.lab4.task2;

import java.util.Arrays;

public class Garden {
    public static final int size = 10;
    private int[][] state=new int[size][size];
    //0 - a wilted plant
    // 1-2 - ok
    // 3 - max
    Garden(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                state[i][j]=3;
            }
        }
    }

    public boolean isDried(int i, int j){
        return state[i][j]==0;
    }
    public boolean isPlantMax(int i, int j){
        return state[i][j]==3;
    }
    public void waterPlant(int i, int j){
        state[i][j]=3;
    }
    public void divide(int i, int j, int subtractor){
        state[i][j]=(state[i][j]-subtractor)<0 ? 0 :state[i][j]-subtractor ;
    }

    @Override
    public String toString() {
        StringBuilder result=new StringBuilder("");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.append(state[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
