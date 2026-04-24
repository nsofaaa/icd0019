package poly.customer;

import java.util.Objects;

public final class GoldCustomer extends AbstractCustomer {

    public GoldCustomer(String id, String name, int bonusPoints) {
        super(id, name, bonusPoints);
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order.total() >= 100) {
            bonusPoints += (int) (order.total() * 1.5);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String asString() {
        return String.format("GOLD;%s;%s;%d;", id, name, bonusPoints);
    }

}