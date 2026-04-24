package poly.customer;

import java.time.LocalDate;
import java.util.Objects;

public final class RegularCustomer extends AbstractCustomer {

    private LocalDate lastOrderDate;

    public RegularCustomer(String id, String name,
                           int bonusPoints, LocalDate lastOrderDate) {
        super(id, name, bonusPoints);
        this.lastOrderDate = lastOrderDate;
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.total() >= 100) {
            double multiplier = 1.0;
            if (lastOrderDate != null && order.date().isBefore(lastOrderDate.plusMonths(1))) {
                multiplier = 1.5;
            }
            bonusPoints += (int) (order.total() * multiplier);
        }
        lastOrderDate = order.date();
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        RegularCustomer that = (RegularCustomer) obj;
        return Objects.equals(lastOrderDate, that.lastOrderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lastOrderDate);
    }

    @Override
    public String asString() {
        return String.format("REGULAR;%s;%s;%d;%s", id, name, bonusPoints, lastOrderDate);
    }

}