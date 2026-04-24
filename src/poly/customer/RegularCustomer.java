package poly.customer;

import java.time.LocalDate;

public final class RegularCustomer extends AbstractCustomer {

    public RegularCustomer(String id, String name, int bonusPoints, LocalDate lastOrderDate) {
        super(id, name, bonusPoints, lastOrderDate);
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.getAmount() >= 100) {
            int toAdd = 200;

            if (lastOrderDate != null && order.date().isBefore(lastOrderDate.plusMonths(1))) {
                toAdd += 100;
            }
            this.bonusPoints += toAdd;
            this.lastOrderDate = order.date();
        }
    }

    @Override
    public String asString() {
        return "REGULAR;" + id + ";" + name + ";" + bonusPoints + ";" + (lastOrderDate != null ? lastOrderDate : "");
    }
}