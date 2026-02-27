package junit;

public class Code {

    public static boolean isSpecial(int candidate) {
        if (candidate % 11 == 0) return true;

        return (candidate + 1) % 11 == 0
                || (candidate + 2) % 11 == 0
                || (candidate + 3) % 11 == 0;
    }

    public static int longestStreak(String inputString) {
        if (inputString == null || inputString.isEmpty()) return 0;

        int max = 1;
        int current = 1;

        for (int i = 1; i < inputString.length(); i++) {
            if (inputString.charAt(i) == inputString.charAt(i - 1)) {
                current++;
                if (current > max) max = current;
            } else {
                current = 1;
            }
        }

        return max;
    }

    public static Character mode(String inputString) {
        if (inputString == null || inputString.isEmpty()) return null;

        char[] chars = inputString.toCharArray();
        int maxCount = 0;
        Character result = null;

        for (int i = 0; i < chars.length; i++) {
            int count = getCharacterCount(inputString, chars[i]);
            if (count > maxCount) {
                maxCount = count;
                result = chars[i];
            }
        }

        return result;
    }

    public static int getCharacterCount(String allCharacters, char targetCharacter) {
        if (allCharacters == null) return 0;

        int count = 0;
        for (int i = 0; i < allCharacters.length(); i++) {
            if (allCharacters.charAt(i) == targetCharacter) count++;
        }
        return count;
    }

    public static int[] removeDuplicates(int[] integers) {
        if (integers == null) return null;

        int[] temp = new int[integers.length];
        int size = 0;

        for (int i = 0; i < integers.length; i++) {
            if (!contains(temp, size, integers[i])) {
                temp[size++] = integers[i];
            }
        }

        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = temp[i];
        }

        return result;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        if (integers == null) return 0;

        int[] unique = removeDuplicates(integers);
        int sum = 0;

        for (int i = 0; i < unique.length; i++) {
            sum += unique[i];
        }

        return sum;
    }

    private static boolean contains(int[] array, int size, int value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value) return true;
        }
        return false;
    }
}