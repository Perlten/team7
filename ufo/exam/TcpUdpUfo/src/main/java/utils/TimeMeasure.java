package utils;

public class TimeMeasure {

    private long startTime = 0;

    public void startTimer(){
        this.startTime = System.nanoTime();
    }

    public long endTimer(){
        long currentTime = System.nanoTime();
        long deltaTime = currentTime - this.startTime;
        return deltaTime;
    }

}
