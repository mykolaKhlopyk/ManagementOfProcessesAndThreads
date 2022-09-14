package main.java.ua.mykola.lab2.task1;

public class Field {
    private boolean[][] area;
    Field(int numberOfRow, int numberOfColumn){
        area = new boolean[numberOfRow][numberOfColumn];
    }
    public int getNumberOfRow(){
        return area.length;
    }
    public int getNumberOfColumn(){
        return area[0].length;
    }
    public boolean checkIfPointIsReserved(int x, int y){

        return area[y][x];
    }
    public void doReservation(int x, int y){
        area[y][x] = true;
    }
    public void undoReservation(int x, int y){
        area[y][x]=false;
    }


}
