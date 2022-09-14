package main.java.ua.mykola.lab2.task1;

public class Bear extends Thread{
    public boolean isAlive;
    Field field;
    int speedX, speedY, x, y;
    private static final int timeWaiting = 400;
    private static final int limit = 2;
    Bear(Field field){
        isAlive=true;
        this.field=field;
        speedX=speedY=1;
        x=y=10;
    }
    public void kill(){
        this.isAlive=false;
    }

    @Override
    public void run() {
        while (isAlive){
            if (field.checkIfPointIsReserved(x, y)) {
                isAlive=false;
                return;
            }
            field.doReservation(x, y);
            try {
                Thread.sleep((int)(timeWaiting * Math.random() * (limit+1)));
            } catch (Exception e) {
            }
            field.undoReservation(x, y);
            move();
        }
        System.out.println(x+" "+y);
    }
    public void move(){
        if (x+speedX<0 || x+speedX>=field.getNumberOfColumn()|| (((int)(Math.random()*10))==1 && x>2 && x<10))
            speedX*=-1;
        if (y+speedY<0 || y+speedY>=field.getNumberOfRow() || (((int)(Math.random()*10))==1 && y>2 && y<10))
            speedY*=-1;
        if (!field.checkIfPointIsReserved(x, y)){
            x+=speedX;
            y+=speedY;
        }

    }



    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
