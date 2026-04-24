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
                .filter(customer -> customer.getId().equals(id))
                .map(this::copy)
                .findFirst();
    }

    public void remove(String id) {
        customers.removeIf(customer -> customer.getId().equals(id));
    }

    public void save(AbstractCustomer customer) {
        remove(customer.getId());
        customers.add(copy(customer));
    }

    public int getCustomerCount() {
        return customers.size();
    }

    private AbstractCustomer copy(AbstractCustomer customer) {
        if (customer instanceof RegularCustomer) {
            return new RegularCustomer(customer.id, customer.name, customer.bonusPoints, customer.lastOrderDate);
        } else {
            return new GoldCustomer(customer.id, customer.name, customer.bonusPoints, customer.lastOrderDate);
        }
    }

    public List<AbstractCustomer> getAllPaged(int pageNumber, int pageSize) {
        return List.of(); // only needed for icd0019app project
    }
}
