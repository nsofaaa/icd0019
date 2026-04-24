package poly.customer;

import java.time.LocalDate;
import java.util.Objects;

public abstract sealed class AbstractCustomer
        permits GoldCustomer, RegularCustomer {

    protected String id;
    protected String name;
    protected int bonusPoints;
    protected LocalDate lastOrderDate;

    public AbstractCustomer(String id, String name, int bonusPoints, LocalDate lastOrderDate) {
        this.id = id;
        this.name = name;
        this.bonusPoints = bonusPoints;
        this.lastOrderDate = lastOrderDate;
    }

    public abstract void collectBonusPointsFrom(Order order);

    /**
     * Provides string representation of the object.
     * This string can be used for storing the object in text file.
     * @return string representation of the object.
     */
    public abstract String asString();

    public String getId() { return id; }
    public String getName() { return name; }
    public Integer getBonusPoints() { return bonusPoints; }

    public LocalDate getLastOrderDate() {
        return lastOrderDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        AbstractCustomer other = (AbstractCustomer) obj;
        return Objects.equals(id, other.id) &&
                Objects.equals(name, other.name) &&
                bonusPoints == other.bonusPoints &&
                Objects.equals(lastOrderDate, other.lastOrderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bonusPoints, lastOrderDate);
    }

    @Override
    public String toString() {
        return asString();
    }
}