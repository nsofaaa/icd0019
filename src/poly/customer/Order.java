package poly.customer;

import java.time.LocalDate;

public record Order(double total, LocalDate date) {
    public int getAmount() {
        return (int) total;
    }
}
