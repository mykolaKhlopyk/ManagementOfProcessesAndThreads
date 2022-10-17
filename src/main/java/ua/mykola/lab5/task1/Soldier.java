package main.java.ua.mykola.lab5.task1;

public class Soldier {
    private SIDE side;
    private boolean haveToTurn=false;
    public Soldier() {
        this.side = SIDE.FORWARD;
    }
    public void turnLeft(){
        side=SIDE.LEFT;
    }
    public void turnRight(){
        side=SIDE.LEFT;
    }
    public SIDE getSide() {
        return side;
    }
    public void turn(){
        side=(side==SIDE.LEFT)?SIDE.RIGHT:SIDE.LEFT;
    }
    public void setTrueHaveToTurn(){
        haveToTurn=true;
    }

    public boolean isHaveToTurn() {
        return haveToTurn;
    }
}
