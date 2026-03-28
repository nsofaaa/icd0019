package collections.sorting;

import java.util.Comparator;
import java.util.List;

public class LengthAndElementReverseComparator implements Comparator<List<Character>> {

    @Override
    public int compare(List<Character> a, List<Character> b) {
        if (a.size() != b.size()) {
            return Integer.compare(b.size(), a.size());
        }
        return b.get(0).compareTo(a.get(0));
        }
    }

