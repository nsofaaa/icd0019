package collections.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberString implements Comparable<NumberString> {
    public String initialValue;
    public List<List<Character>> streaks;

    public NumberString(String input) {
        initialValue = input;
        List<Character> chars = new ArrayList<>();
        for (char c : input.toCharArray()) {
            chars.add(c);
        }

        this.streaks = getStreakList(chars);
        this.streaks.sort(new LengthAndElementReverseComparator());
    }

    public String initialValue() {
        return initialValue;
    }

    public static List<List<Character>> getStreakList(List<Character> characters) {
        if (characters.isEmpty()) return List.of();

        List<Character> sortedChars = new ArrayList<>(characters);
        Collections.sort(sortedChars);

        List<List<Character>> result = new ArrayList<>();
        List<Character> currentStreak = new ArrayList<>();

        for (Character c : sortedChars) {
            if (currentStreak.isEmpty() || currentStreak.get(0).equals(c)) {
                currentStreak.add(c);
            } else {
                result.add(currentStreak);
                currentStreak = new ArrayList<>();
                currentStreak.add(c);
            }
        }
        result.add(currentStreak);

        return result;
    }

    @Override
    public int compareTo(NumberString o) {
        LengthAndElementReverseComparator comparator = new LengthAndElementReverseComparator();

        int minSize = Math.min(this.streaks.size(), o.streaks.size());
        for (int i = 0; i < minSize; i++) {
            int res = comparator.compare(this.streaks.get(i), o.streaks.get(i));
            if (res != 0) return -res;
        }

        return Integer.compare(this.streaks.size(), o.streaks.size());
    }
}