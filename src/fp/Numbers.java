package fp;

import org.junit.jupiter.api.Test;

import java.util.List;

public class Numbers {

    private final List<Integer> numbers = List.of(1, 3, 4, 51, 24, 5);

    @Test
    public void findsOddNumbers() {
        List<Integer> oddNumbers = numbers.stream()
                .filter(n -> n % 2 != 0) //add filter here
                .toList();

        System.out.println(oddNumbers);
    }

    @Test
    public void findsOddNumbersOver10() {
        List<Integer> result = numbers.stream()
                .filter(n -> n % 2 != 0)
                .filter(n -> n > 10)
                .toList();

        System.out.println(result);
    }


    @Test
    public void findsSquaredOddNumbers() {
        List<Integer> result = numbers.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * n)
                .toList();

        System.out.println(result);
    }

    @Test
    public void findsSumOfOddNumbers() {
        Integer sum = numbers.stream()
                .filter(n -> n % 2 != 0)
                .mapToInt(n -> n)
                .sum();

        System.out.println(sum);
    }
}