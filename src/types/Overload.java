package types;

public class Overload {

    public static void main(String[] args) {
        add(1, 2);          // int
        add(1L, 2L);        // long
        add("3", "4");      // String
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
