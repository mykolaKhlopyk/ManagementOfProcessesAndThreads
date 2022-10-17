package main.java.ua.mykola.lab5.task1;

public class Turn extends Thread{
    private int start;
    private int numberOfSoldiers;
    SoldiersRaw soldiersRaw;
    boolean isAlive = true;
    public  Turn(int start, int numberOfSoldiers, SoldiersRaw soldiersRaw){
        this.start=start;
        this.numberOfSoldiers=numberOfSoldiers;
        this.soldiersRaw=soldiersRaw;
    }
    @Override
    public synchronized void run() {
        while (isAlive){
            findSoldiersWhichHaveToReturn();
            turnSoldiers();
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void findSoldiersWhichHaveToReturn(){
        if (start == 0 && soldiersRaw.getSoldiers(0).getSide()==SIDE.RIGHT){
            soldiersRaw.getSoldiers(0).setTrueHaveToTurn();
        }
        for (int i = start; i < start+numberOfSoldiers; i++) {
            Soldier currentSoldier = soldiersRaw.getSoldiers(i);
            Soldier nextSoldier = soldiersRaw.getSoldiers(i+1);
            if (nextSoldier == null && currentSoldier.getSide()==SIDE.RIGHT){
                currentSoldier.setTrueHaveToTurn();
                continue;
            }
            if (currentSoldier.getSide()==SIDE.RIGHT && nextSoldier.getSide()==SIDE.LEFT){
                nextSoldier.setTrueHaveToTurn();
                nextSoldier.setTrueHaveToTurn();
            }
        }
    }
    private void turnSoldiers(){
        for (int i = start; i < start+numberOfSoldiers; i++) {
            if (soldiersRaw.getSoldiers(i).isHaveToTurn())
                soldiersRaw.getSoldiers(i).turn();
        }
    }
}