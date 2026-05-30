package reflection.tester;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface MyTest {
    String[] tags() default {};
}
