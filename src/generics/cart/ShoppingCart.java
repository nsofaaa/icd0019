package generics.cart;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCart<T extends CartItem> {

    private final Map<String, CartEntry> items = new LinkedHashMap<>();
    private final List<Double> discounts = new ArrayList<>();
    private Double couponDiscount = 0.0;

    private final Map<String, Double> couponToDiscount = Map.of(
            "Sale5", 5.0,
            "Sale8", 8.0,
            "Sale10", 10.0);

    public void add(T item) {
        if (items.containsKey(item.id())) {
            items.get(item.id()).quantity++;
        } else {
            items.put(item.id(), new CartEntry(item, 1));
        }
    }

    public void addAll(List<T> itemsToAdd) {
        itemsToAdd.forEach(this::add);
    }

    public void removeById(String id) {
        items.remove(id);
    }

    public Double getTotal() {
        double subtotal = items.values().stream()
                .mapToDouble(entry -> entry.item.price() * entry.quantity)
                .sum();

        double factor = (100.0 - getTotalDiscount()) / 100.0;
        return subtotal * factor;
    }

    public List<CartEntry> getContents() {
        return new ArrayList<>(items.values());
    }

    public void increaseQuantity(String id) {
        if (items.containsKey(id)) {
            items.get(id).quantity++;
        }
    }

    public void applyDiscountPercentage(Double discount) {
        discounts.add(discount);
    }

    public boolean applyCoupon(String coupon) {
        if (couponToDiscount.containsKey(coupon)) {
            couponDiscount = couponToDiscount.get(coupon);
            return true;
        }
        return false;
    }

    public Double getTotalDiscount() {
        double currentTotalDiscount = 0.0;

        for (Double d : discounts) {
            currentTotalDiscount = currentTotalDiscount + d - (currentTotalDiscount * d / 100.0);
        }

        if (couponDiscount > 0) {
            currentTotalDiscount = currentTotalDiscount + couponDiscount - (currentTotalDiscount * couponDiscount / 100.0);
        }

        return currentTotalDiscount;
    }

    public void removeLastDiscount() {
        if (!discounts.isEmpty()) {
            discounts.remove(discounts.size() - 1);
        }
    }

    @Override
    public String toString() {
        return items.values().stream()
                .map(CartEntry::toString)
                .collect(Collectors.joining(", "));
    }
}