package poly.customer;

import java.time.LocalDate;

public final class GoldCustomer extends AbstractCustomer {

    public GoldCustomer(String id, String name, int bonusPoints, LocalDate lastOrderDate) {
        super(id, name, bonusPoints, lastOrderDate);
    }

    public GoldCustomer(String id, String name, int bonusPoints) {
        this(id, name, bonusPoints, null);
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.total() >= 100) {
            this.bonusPoints += (int) (order.total() * 1.5);
            this.lastOrderDate = order.date();
        }
    }

    @Override
    public String asString() {
        String dateStr = lastOrderDate != null ? lastOrderDate.toString() : "";
        return "GOLD;" + id + ";" + name + ";" + bonusPoints + ";" + dateStr;
    }
}