package icd0019types.src.types;

public class Overload {

    public static void main(String[] args) {
        int intResult = add(2, 3);
        System.out.println("Result: " + intResult);

        long longResult = add(2L, 3L);
        System.out.println("Result: " + longResult);

        long stringResult = add("10", "20");
        System.out.println("Result: " + stringResult);
    }

    public static long add(long x, long y) {
        System.out.println("Adding longs");
        return x + y;
    }

    public static int add(int x, int y) {
        System.out.println("Adding integers");
        return x + y;
    }

    public static long add(String x, String y) {
        System.out.println("Adding numbers from strings");
        return Long.parseLong(x) + Long.parseLong(y);
    }

}
