package main.java.ua.mykola.lab2.task1;

public class Bee extends Thread {
    private int rowForSearching;
    private Field field;
    private Bear bear;
    private Bag bag;
    private static final int timeWaiting = 200;
    private static final int limit = 3;
    private int x;

    Bee(Field field, Bear bear, Bag bag) {

        this.field = field;
        this.bear = bear;
        this.bag = bag;
        this.rowForSearching = bag.getUncheckedRow(null);;
    }

    @Override
    public void run() {
        while (bear.isAlive) {
            for (int column = 0; column < field.getNumberOfColumn(); column++) {

                x=column;
                if (!bear.isAlive || field.checkIfPointIsReserved(column, rowForSearching)) {
                    bear.kill();
                    return;
                }
                field.doReservation(column, rowForSearching);
                try {
                    Thread.sleep((int) (timeWaiting * Math.random() * (limit + 1)));
                } catch (Exception e) {
                }
                field.undoReservation(column, rowForSearching);
            }
            rowForSearching=bag.getUncheckedRow(this);
        }
    }


    public int getX() {
        return x;
    }

    public int getRowForSearching() {
        return rowForSearching;
    }
}
