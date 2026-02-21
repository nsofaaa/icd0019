package icd0019types.src.types;

import java.util.Arrays;
import java.util.Random;

public class Code {

    public static void main(String[] args) {

        int[] numbers = {1, 3, -2, 9};

        System.out.println(sum(numbers)); // 11
    }

    public static int sum(int[] numbers) {
        return 0;
    }

    public static double average(int[] numbers) {
        return 0.0;
    }

    public static Integer minimumElement(int[] integers) {
        return null;
    }

    public static String asString(int[] elements) {
        return "";
    }

    public static Character mode(String input) {
        return null;
    }

    public static String squareDigits(String s) {
        return "";
    }

    public static int isolatedSquareCount() {
        boolean[][] table = getSampleTable();

        int isolatedCount = 0;

        // count isolates squares here

        return isolatedCount;
    }

    public static boolean isIsolated(int row, int col) {
        boolean[][] table = getSampleTable();

        printTable(table);

        return false;
    }

    private static void printTable(boolean[][] table) {
        for (boolean[] row : table) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static boolean[][] getSampleTable() {
        boolean[][] table = new boolean[10][10];

        Random r = new Random(5);
        for (boolean[] row : table) {
            for (int i = 0; i < row.length; i++) {
                row[i] = r.nextInt(5) < 2;
            }
        }

        return table;
    }
}
