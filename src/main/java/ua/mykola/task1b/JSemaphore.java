package main.java.ua.mykola.task1b;

public class JSemaphore {
    private int threadNumber;

    //1 - first thread
    //2 - second
    //0 - semaphore is not reversed
    JSemaphore() {
        threadNumber = 0;
    }

    public void clear() {
        threadNumber = 0;
    }

    public boolean setThread(int threadNumber) {
        if (isNotReserved()) {
            this.threadNumber = threadNumber;
            return true;
        }
        return false;
    }

    public boolean isNotReserved(){
        if (threadNumber == 0)
            return true;
        return false;
    }

    public int getThreadNumber() {
        return threadNumber;
    }
}
