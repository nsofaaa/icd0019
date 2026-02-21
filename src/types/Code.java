package icd0019types.src.types;

import java.util.Random; //po

public class Code {

    // ✅ Таблица теперь хранится внутри класса
    private static final boolean[][] table = getSampleTable();

    public static void main(String[] args) {

        int[] numbers = {1, 3, -2, 9};

        System.out.println("Sum: " + sum(numbers));
        System.out.println("Average: " + average(numbers));
        System.out.println("Minimum: " + minimumElement(numbers));
        System.out.println("As String: " + asString(numbers));

        String s = "abccbc";
        System.out.println("Mode: " + mode(s));

        System.out.println("Square Digits: " + squareDigits("a9b2"));

        int isolatedCount = isolatedSquareCount();
        System.out.println("Isolated squares count: " + isolatedCount);

        System.out.println("Is (0,9) isolated? " + isIsolated(0, 9));
        System.out.println("Is (5,7) isolated? " + isIsolated(5, 7));
    }

    public static int sum(final int[] numbers) {
        int total = 0;
        for (final int n : numbers) {
            total += n;
        }
        return total;
    }

    public static double average(final int[] numbers) {
        if (numbers.length == 0) {
            return 0.0;
        }
        return (double) sum(numbers) / numbers.length;
    }

    public static Integer minimumElement(final int[] integers) {
        if (integers.length == 0) {
            return null;
        }
        int min = integers[0];
        for (int n : integers) {
            if (n < min) {
                min = n;
            }
        }
        return min;
    }

    public static String asString(final int[] elements) {
        if (elements.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            sb.append(elements[i]);
            if (i < elements.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static Character mode(final String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        int[] count = new int[256];

        for (char c : input.toCharArray()) {
            count[c]++;
        }

        char result = input.charAt(0);
        int max = 0;

        for (char c : input.toCharArray()) {
            if (count[c] > max) {
                max = count[c];
                result = c;
            }
        }

        return result;
    }

    public static String squareDigits(final String s) {
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                int num = c - '0';
                sb.append(num * num);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    // ✅ Без параметров
    public static int isolatedSquareCount() {
        int isolatedCount = 0;

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] && isIsolated(i, j)) {
                    isolatedCount++;
                }
            }
        }

        return isolatedCount;
    }

    // ✅ Без передачи matrix
    public static boolean isIsolated(final int row, final int col) {

        int rows = table.length;
        int cols = table[0].length;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {

                if (i == row && j == col) {
                    continue;
                }

                if (i >= 0 && i < rows && j >= 0 && j < cols) {
                    if (table[i][j]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean[][] getSampleTable() {
        boolean[][] table = new boolean[10][10];
        Random r = new Random(5);

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = r.nextInt(5) < 2;
            }
        }

        return table;
    }
}