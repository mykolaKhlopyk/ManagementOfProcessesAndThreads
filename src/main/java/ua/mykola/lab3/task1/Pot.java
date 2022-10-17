package main.java.ua.mykola.lab3.task1;

public class Pot {
    private  int occupancyPercentage;//max 100
    public boolean isReserved=false;
    public boolean isFull=false;
    private Bear pooh;

    Pot() {
        occupancyPercentage = 0;
    }

    public synchronized void putHoneyInPot(int num, int number) throws InterruptedException {
        occupancyPercentage +=num;
        if (occupancyPercentage>100)
            occupancyPercentage=100;
        System.out.println("bee number " +number+" "+occupancyPercentage);
        if (occupancyPercentage>=100) {
            isFull=true;
            pooh.start();
        }
    }

    public void getHoney(){
        occupancyPercentage-=10;
        if (occupancyPercentage == 0) {
            isFull=false;
            System.out.println("Bear has eaten honey, pot is empty");
        }
        System.out.println("Bear eats honey, in pot left "+ occupancyPercentage+" pieces of honey");
    }
    public void setBear(Bear pooh){
        this.pooh=pooh;
    }
}
