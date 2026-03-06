package oo.hide;

public class Counter {

    private int value;
    private int step;

    public Counter(int start, int step) {
        this.value = start;
        this.step = step;
    }

    public int nextValue() {
        value += step;
        return value;
    }
}