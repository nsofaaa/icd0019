package poly.customer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomerRepository {
    private final Map<String, AbstractCustomer> customers = new HashMap<>();

    public CustomerRepository() {
        save(new RegularCustomer("c1", "Anna", 0, LocalDate.parse("2024-03-15")));
        save(new RegularCustomer("c2", "Bob", 0, LocalDate.parse("2020-01-01")));
        save(new GoldCustomer("c3", "John", 0, LocalDate.now()));
    }

    public void save(AbstractCustomer customer) {
        customers.put(customer.getId(), copy(customer));
    }

    public Optional<AbstractCustomer> getCustomerById(String id) {
        return Optional.ofNullable(customers.get(id)).map(this::copy);
    }

    public void remove(String id) {
        customers.remove(id);
    }

    public int getCustomerCount() {
        return customers.size();
    }

    private AbstractCustomer copy(AbstractCustomer c) {
        if (c instanceof RegularCustomer) {
            return new RegularCustomer(c.getId(), c.getName(), c.getBonusPoints(), c.getLastOrderDate());
        } else {
            return new GoldCustomer(c.getId(), c.getName(), c.getBonusPoints(), c.getLastOrderDate());
        }
    }
}