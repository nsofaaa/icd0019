package junit;

public class Code {
    public static boolean isSpecial(int candidate) {
        int remainder = candidate % 11;

        return remainder == 0 || remainder <= 3;
    }

    public static int longestStreak(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return 0;
        }

        int max = 1;
        int current = 1;

        for (int i = 1; i < inputString.length(); i++) {
            if (inputString.charAt(i) == inputString.charAt(i - 1)) {
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

        int maxCount = 0;
        Character result = null;

        for (char c : inputString.toCharArray()) {
            int count = getCharacterCount(inputString, c);

            if (count > maxCount) {
                maxCount = count;
                result = c;
            }
        }

        return result;
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

        for (int value : integers) {
            if (!contains(temp, size, value)) {
                temp[size] = value;
                size++;
            }
        }

        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            result[i] = temp[i];
        }

        return result;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        if (integers == null) {
            return 0;
        }

        int sum = 0;
        int[] unique = removeDuplicates(integers);

        for (int value : unique) {
            sum += value;
        }

        return sum;
    }

    private static boolean contains(int[] array, int size, int value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                return true;
            }
        }

        return false;
    }
}