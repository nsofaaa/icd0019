package poly.customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    private List<AbstractCustomer> customers = new ArrayList<>();

    public CustomerRepository() {
        save(new RegularCustomer("c1", "Alice", 0, LocalDate.parse("2024-03-10")));
        save(new RegularCustomer("c2", "Bob", 0, LocalDate.parse("2024-01-04")));
        save(new GoldCustomer("c3", "Carol", 0, null));
    }

    public Optional<AbstractCustomer> getCustomerById(String id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .map(this::copy)
                .findFirst();
    }

    public void remove(String id) {
        customers.removeIf(c -> c.getId().equals(id));
    }

    public void save(AbstractCustomer customer) {
        remove(customer.getId());
        customers.add(copy(customer));
    }

    public int getCustomerCount() {
        return customers.size();
    }

    private AbstractCustomer copy(AbstractCustomer c) {
        if (c instanceof RegularCustomer) {
            return new RegularCustomer(c.id, c.name, c.bonusPoints, c.lastOrderDate);
        } else {
            return new GoldCustomer(c.id, c.name, c.bonusPoints, c.lastOrderDate);
        }
    }

    public List<AbstractCustomer> getAllPaged(int pageNumber, int pageSize) {
        return List.of();
    }
}