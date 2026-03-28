package collections.set;

import org.junit.jupiter.api.Test;
import java.util.Set;

import java.util.HashSet;
import java.util.Random;


public class Birthday {

    @Test
    public void runCode() {

        int sizes = 0;
        for (int i = 0; i < 1000; i ++) {
            sizes += getGroupSize();
        }

        System.out.println(sizes / 1000);
    }

    private static int getGroupSize() {

        Random r = new Random();

        int randomDayOfYear = r.nextInt(365);

        System.out.println(randomDayOfYear);

        Set<Integer> covered = new HashSet<>();

        while (!covered.contains(randomDayOfYear)) {

            covered.add(randomDayOfYear);

            randomDayOfYear = r.nextInt(365);
        }

        System.out.println(covered.size() + 1);

        return covered.size();

    }

}
