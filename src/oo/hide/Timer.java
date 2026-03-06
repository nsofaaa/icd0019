package oo.hide;

public class Timer {

    private long startTime;

    public Timer() {
        startTime = System.currentTimeMillis();
    }

    public String getPassedTime() {

        long now = System.currentTimeMillis();

        long seconds = (now - startTime) / 1000;

        return String.valueOf(seconds);
    }
}