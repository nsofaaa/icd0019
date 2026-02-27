package junit;

public class Code {

    public static boolean isSpecial(int candidate) {
        return candidate % 11 <= 3;
    }

    public static int longestStreak(String inputString) {
        if (inputString == null || inputString.isEmpty()) return 0;
        char[] chars = inputString.toCharArray();
        int max = 1, count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) count++;
            else count = 1;
            if (count > max) max = count;
        }
        return max;
    }

    public static Character mode(String inputString) {
        if (inputString == null || inputString.isEmpty()) return null;
        char[] chars = inputString.toCharArray();
        int maxCount = 0;
        char result = chars[0];
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
        int count = 0;
        for (int i = 0; i < allCharacters.length(); i++)
            if (allCharacters.charAt(i) == targetCharacter) count++;
        return count;
    }

    public static int[] removeDuplicates(int[] integers) {
        if (integers == null) return new int[0];
        int[] temp = new int[integers.length];
        int size = 0;
        for (int i = 0; i < integers.length; i++) {
            boolean exists = false;
            for (int j = 0; j < size; j++) {
                if (temp[j] == integers[i]) {
                    exists = true;
                    break;
                }
            }
            if (!exists) temp[size++] = integers[i];
        }
        int[] result = new int[size];
        for (int i = 0; i < size; i++) result[i] = temp[i];
        return result;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        int[] unique = removeDuplicates(integers);
        int sum = 0;
        for (int i = 0; i < unique.length; i++) sum += unique[i];
        return sum;
    }
}