package main.java.ua.mykola.lab5.task1;

import java.util.ArrayList;

public class SoldiersRaw {
    private Soldier[] soldiers = new Soldier[numberOfSoldiers];
    private boolean[] isDonePart = new boolean[numberOfParts];
    private static final int numberOfSoldiers = 150;
    private static final int numberOfParts = 3;

    public SoldiersRaw() {
        this.soldiers = new Soldier[numberOfSoldiers];
        for (Soldier soldier : soldiers) {
            if (Math.random() > 0.5)
                soldier.turnLeft();
            else
                soldier.turnRight();
        }
    }

    public Soldier getSoldiers(int index) {
        return (index >= 0 && index < numberOfSoldiers) ? soldiers[index] : null;
    }

    public void finishTurningPart(int part) {
        this.isDonePart[part] = true;
    }

    public void cleanParts() {
        for (int i = 0; i < numberOfParts; i++) {
            this.isDonePart[i] = false;
        }
    }



}
