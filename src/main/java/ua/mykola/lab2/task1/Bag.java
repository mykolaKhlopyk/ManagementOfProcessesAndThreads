package main.java.ua.mykola.lab2.task1;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Bag {
    Queue<Integer> listOfSection;
    Bag(int numberOfRows) {
        listOfSection=new LinkedList<>();
        for (int i = 0; i < numberOfRows; i++) {
            listOfSection.add(i);
        }
    }
    public int getUncheckedRow(Bee bee) {
        if (bee != null){
            listOfSection.add(bee.getRowForSearching());
        }
        return listOfSection.poll();
    }
}
