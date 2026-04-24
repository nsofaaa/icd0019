package poly;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AverageTest {

    @Test
    public void computesAverageFromListOfNumbers() {

        Double result = getAverage(List.of(3.9, 4, 1.2, 0, 9, 2));

        assertThat(result, is(closeTo(3.35)));
    }

    public Double getAverage(List<? extends Number> numbers) {
        return numbers.stream()
                .mapToDouble(Number::doubleValue)
                .average()
                .orElseThrow(IllegalArgumentException::new);
    }

    private org.hamcrest.Matcher<Double> closeTo(double value) {
        double precision = 0.001;

        return Matchers.closeTo(value, precision);
    }

}
