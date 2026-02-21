public static int sum(int[] numbers) {
    int sum = 0;
    for (int n : numbers) {
        sum += n;
    }
    return sum;
}

public static double average(int[] numbers) {
    if (numbers.length == 0) return 0;
    return (double) sum(numbers) / numbers.length;
}

public static Integer minimumElement(int[] integers) {
    if (integers.length == 0) return null;

    int min = integers[0];
    for (int n : integers) {
        if (n < min) min = n;
    }
    return min;
}

public static String asString(int[] elements) {
    String result = "";

    for (int i = 0; i < elements.length; i++) {
        result += elements[i];
        if (i < elements.length - 1) {
            result += ", ";
        }
    }

    return result;
}

public static Character mode(String input) {
    if (input == null || input.length() == 0) return null;

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

public static String squareDigits(String s) {
    String result = "";

    for (char c : s.toCharArray()) {
        if (Character.isDigit(c)) {
            int num = Integer.parseInt("" + c);
            result += num * num;
        } else {
            result += c;
        }
    }

    return result;
}

public static boolean isIsolated(int row, int col) {
    boolean[][] table = getSampleTable();

    for (int i = row - 1; i <= row + 1; i++) {
        for (int j = col - 1; j <= col + 1; j++) {

            if (i == row && j == col) continue;

            if (i >= 0 && i < table.length && j >= 0 && j < table[0].length) {
                if (table[i][j]) {
                    return false;
                }
            }
        }
    }

    return true;
}

public static int isolatedSquareCount() {
    boolean[][] table = getSampleTable();

    int count = 0;

    for (int i = 0; i < table.length; i++) {
        for (int j = 0; j < table[i].length; j++) {
            if (table[i][j] && isIsolated(i, j)) {
                count++;
            }
        }
    }

    return count;
}

void main() {
}
