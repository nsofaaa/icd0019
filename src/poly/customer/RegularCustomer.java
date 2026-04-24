package poly.customer;

import java.time.LocalDate;

public final class RegularCustomer extends AbstractCustomer {

    public RegularCustomer(String id, String name, int bonusPoints, LocalDate lastOrderDate) {
        super(id, name, bonusPoints, lastOrderDate);
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.getAmount() >= 100) {
            int pointsToAdd = order.getAmount();

            if (lastOrderDate != null && order.date().isBefore(lastOrderDate.plusMonths(1))) {
                pointsToAdd += 100;
            }

            this.bonusPoints += pointsToAdd;
            this.lastOrderDate = order.date();
        }
    }

    @Override
    public String asString() {
        return "REGULAR;" + id + ";" + name + ";" + bonusPoints + ";" + lastOrderDate;
    }
}