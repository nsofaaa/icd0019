package generics.cart;

import java.util.List;
import java.util.Map;

public class ShoppingCart<T> {

    private final Map<String, Double> couponToDiscount = Map.of(
            "Sale5", 5.0,
            "Sale8", 8.0,
            "Sale10", 10.0);

    public void add(T item) {
        throw new RuntimeException("not implemented yet");
    }

    public void removeById(String id) {
        throw new RuntimeException("not implemented yet");
    }

    public Double getTotal() {
        throw new RuntimeException("not implemented yet");
    }

    public List<CartEntry> getContents() {
        throw new RuntimeException("not implemented yet");
    }

    public void increaseQuantity(String id) {
        throw new RuntimeException("not implemented yet");
    }

    public void applyDiscountPercentage(Double discount) {
        throw new RuntimeException("not implemented yet");
    }

    public boolean applyCoupon(String coupon) {
        throw new RuntimeException("not implemented yet");
    }

    public Double getTotalDiscount() {
        throw new RuntimeException("not implemented yet");
    }

    public void removeLastDiscount() {
        throw new RuntimeException("not implemented yet");
    }

    public void addAll(List<T> items) {
        throw new RuntimeException("not implemented yet");
    }
}