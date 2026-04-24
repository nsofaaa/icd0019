package poly.shapes;

import org.junit.jupiter.api.Test;
import java.util.List;
import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ShapeTest {

    @Test
    public void computesTotalArea() {

        List<Shape> objects = List.of(
                new Circle(5), new Rectangle(2, 4), new Square(3));

        double totalArea = 0.0;
        for (Shape shape : objects) {
            totalArea += shape.getArea();
        }

        assertThat(totalArea, is(closeTo(95.5)));
    }

    private Matcher<Double> closeTo(double value) {
        double precision = 0.1;
        return org.hamcrest.Matchers.closeTo(value, precision);
    }
}