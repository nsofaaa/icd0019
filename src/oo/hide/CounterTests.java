package oo.hide;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CounterTests {

    @Test
    public void keepsCount1() {
        Counter counter = new Counter(1, 3);

        System.out.println(counter.nextValue());
        System.out.println(counter.nextValue());
    }


        @Test
        public void keepsCount() {
            Counter counter = new Counter(1, 1);

        assertThat(counter.nextValue()).isEqualTo(1);
        assertThat(counter.nextValue()).isEqualTo(2);
        assertThat(counter.nextValue()).isEqualTo(3);
    }

    @Test
    public void keepsCountWithStep() {
        Counter counter = new Counter(1, 2);

        assertThat(counter.nextValue()).isEqualTo(1);
        assertThat(counter.nextValue()).isEqualTo(3);
        assertThat(counter.nextValue()).isEqualTo(5);
        assertThat(counter.nextValue()).isEqualTo(7);
    }


}
