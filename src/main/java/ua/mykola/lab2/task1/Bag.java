package main.java.ua.mykola.lab2.task1;

public class Bag {
    boolean[] listOfRows;

    Bag(int numberOfRows) {
        listOfRows = new boolean[numberOfRows];
    }

    public synchronized int getUncheckedRow(Bee bee) {
        int freeRawIndex = listOfRows.length ;
        for (int i = listOfRows.length - 1; i >= 0; i--) {
            if (listOfRows[i]) {
                freeRawIndex = i+1;
                break;
            }
        }
        if (freeRawIndex == listOfRows.length){
            for (int i = 0; i < listOfRows.length - 1; i++) {
                if (!listOfRows[i]) {
                    freeRawIndex = i;
                    break;
                }
            }
        }
        if (bee != null) {
            listOfRows[bee.getRowForSearching()] = false;
        }
        listOfRows[freeRawIndex] = true;
        System.out.println(freeRawIndex);
        return freeRawIndex;
    }
}
