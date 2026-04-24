package poly.customer;

import java.time.LocalDate;
import java.util.Objects;

public final class RegularCustomer extends AbstractCustomer {

    public RegularCustomer(String id, String name, int bonusPoints, LocalDate lastOrderDate) {
        super(id, name, bonusPoints, lastOrderDate);
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.total() >= 100) {
            int points = (int) order.total();
            if (lastOrderDate != null && order.date().isBefore(lastOrderDate.plusMonths(1))) {
                points += 100;
            }
            this.bonusPoints += points;
            this.lastOrderDate = order.date();
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && Objects.equals(lastOrderDate, ((RegularCustomer) obj).lastOrderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lastOrderDate);
    }

    @Override
    public String asString() {
        return "REGULAR;" + id + ";" + name + ";" + bonusPoints + ";" + lastOrderDate;
    }
}