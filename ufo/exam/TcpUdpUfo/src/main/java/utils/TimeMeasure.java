package utils;

public class TimeMeasure {

    private long startTime = 0;

    public void startTimer(){
        this.startTime = System.currentTimeMillis();
    }

    public long endTimer(){
        long currentTime = System.currentTimeMillis();
        long deltaTime = currentTime - this.startTime;
        return deltaTime;
    }

}
