package poly.customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    private static final String FILE_PATH = "src/poly/customer/data.txt";

    private List<AbstractCustomer> customers = new ArrayList<>();

    public CustomerRepository() {
        load();
    }

    private void load() {
        try {
            List<String> lines = Files.readAllLines(Path.of(FILE_PATH));
            for (String line : lines) {
                if (line.isBlank()) continue;
                String[] parts = line.split(";");
                String type = parts[0];
                String id = parts[1];
                String name = parts[2];
                int points = Integer.parseInt(parts[3]);

                if ("GOLD".equals(type)) {
                    customers.add(new GoldCustomer(id, name, points));
                } else if ("REGULAR".equals(type)) {
                    LocalDate lastOrder = parts.length > 4 ? LocalDate.parse(parts[4]) : null;
                    customers.add(new RegularCustomer(id, name, points, lastOrder));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<AbstractCustomer> getCustomerById(String id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public void remove(String id) {
        customers.removeIf(c -> c.getId().equals(id));
        saveToFile();
    }

    public void save(AbstractCustomer customer) {
        remove(customer.getId());
        customers.add(customer);
        saveToFile();
    }

    private void saveToFile() {
        List<String> lines = customers.stream()
                .map(AbstractCustomer::asString)
                .toList();
        try {
            Files.write(Path.of(FILE_PATH), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCustomerCount() {
        return customers.size();
    }

    public List<AbstractCustomer> getAllPaged(int pageNumber, int pageSize) {
        return customers.stream()
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .toList();
    }
}