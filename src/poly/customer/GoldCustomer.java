package poly.customer;

import java.time.LocalDate;

public final class GoldCustomer extends AbstractCustomer {

    public GoldCustomer(String id, String name, int bonusPoints, LocalDate lastOrderDate) {
        super(id, name, bonusPoints, lastOrderDate);
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.getAmount() >= 100) {
            this.bonusPoints += (int) (order.getAmount() * 1.5);
            this.lastOrderDate = order.date();
        }
    }

    @Override
    public String asString() {
        return "GOLD;" + id + ";" + name + ";" + bonusPoints + (lastOrderDate != null ? ";" + lastOrderDate : "");
    }
}