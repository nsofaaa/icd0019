package oo.hide;

public class Fibonacci {

    private int prev = 0;
    private int curr = 1;
    private boolean firstCall = true;

    public int nextValue() {
        if (firstCall) {
            firstCall = false;
            return 0;
        }

        int result = curr;
        int next = prev + curr;

        prev = curr;
        curr = next;

        return result;
    }
}