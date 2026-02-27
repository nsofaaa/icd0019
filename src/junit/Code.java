package junit;

public class Code {

    public static boolean isSpecial(int candidate) {
        return candidate % 11 == 0
                || (candidate - 1) % 11 == 0
                || (candidate - 2) % 11 == 0
                || (candidate - 3) % 11 == 0;
    }

    public static int longestStreak(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return 0;
        }

        int max = 1;
        int current = 1;
        char[] chars = inputString.toCharArray();

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                current++;
                if (current > max) {
                    max = current;
                }
            } else {
                current = 1;
            }
        }

        return max;
    }

    public static Character mode(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return null;
        }

        char bestChar = inputString.charAt(0);
        int bestCount = 0;

        for (char current : inputString.toCharArray()) {
            int count = getCharacterCount(inputString, current);
            if (count > bestCount) {
                bestCount = count;
                bestChar = current;
            }
        }

        return bestChar;
    }

    public static int getCharacterCount(String allCharacters, char targetCharacter) {
        if (allCharacters == null) {
            return 0;
        }

        int count = 0;
        for (char c : allCharacters.toCharArray()) {
            if (c == targetCharacter) {
                count++;
            }
        }
        return count;
    }

    public static int[] removeDuplicates(int[] integers) {
        if (integers == null) {
            return new int[0];
        }

        int[] temp = new int[integers.length];
        int size = 0;

        for (int val : integers) {
            if (!contains(temp, size, val)) {
                temp[size] = val;
                size++;
            }
        }

        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = temp[i];
        }

        return result;
    }

    private static boolean contains(int[] array, int size, int value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        if (integers == null) {
            return 0;
        }

        int[] unique = removeDuplicates(integers);
        int sum = 0;
        for (int val : unique) {
            sum += val;
        }
        return sum;
    }
}