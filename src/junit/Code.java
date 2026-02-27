package junit;

public class Code {

    public static boolean isSpecial(int candidate) {
        if (candidate % 11 == 0) {
            return true;
        } else if ((candidate - 1) % 11 == 0) {
            return true;
        } else if ((candidate - 2) % 11 == 0) {
            return true;
        } else if ((candidate - 3) % 11 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int longestStreak(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return 0;
        }
        int maxStreak = 1;
        int currentStreak = 1;
        char[] chars = inputString.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                currentStreak++;
            } else {
                currentStreak = 1;
            }
            if (currentStreak > maxStreak) {
                maxStreak = currentStreak;
            }
        }
        return maxStreak;
    }

    public static Character mode(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return null;
        }
        char[] chars = inputString.toCharArray();
        char result = chars[0];
        int maxCount = 0;
        for (char c : chars) {
            int count = getCharacterCount(inputString, c);
            if (count > maxCount) {
                maxCount = count;
                result = c;
            }
        }
        return result;
    }

    public static int getCharacterCount(String allCharacters, char targetCharacter) {
        int count = 0;
        char[] chars = allCharacters.toCharArray();
        for (char c : chars) {
            if (c == targetCharacter) {
                count++;
            }
        }
        return count;
    }

    public static int[] removeDuplicates(int[] integers) {
        if (integers == null || integers.length == 0) {
            return new int[0];
        }
        int[] temp = new int[integers.length];
        int newSize = 0;
        for (int value : integers) {
            boolean found = false;
            for (int i = 0; i < newSize; i++) {
                if (temp[i] == value) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                temp[newSize] = value;
                newSize++;
            }
        }
        int[] result = new int[newSize];
        for (int i = 0; i < newSize; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        int sum = 0;
        int[] seen = new int[integers.length];
        int size = 0;
        for (int value : integers) {
            boolean alreadySeen = false;
            for (int i = 0; i < size; i++) {
                if (seen[i] == value) {
                    alreadySeen = true;
                    break;
                }
            }
            if (!alreadySeen) {
                sum += value;
                seen[size] = value;
                size++;
            }
        }
        return sum;
    }
}