package fp.sales;

import fp.Pair;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analyser {

    private final Repository repository;

    private final AccountingService accountingService;

    public Analyser(Repository repository,
                    AccountingService accountingService) {
        this.repository = repository;
        this.accountingService = accountingService;
    }

    public Double getTotalSales() {
        return repository.getEntries().stream()
                .mapToDouble(Entry::amount)
                .sum();
    }

    public Double getSalesByCategory(String category) {
        return repository.getEntries().stream()
                .filter(e -> e.category().equals(category))
                .mapToDouble(Entry::amount)
                .sum();
    }

    public Double getSalesBetween(LocalDate start, LocalDate end) {
        return repository.getEntries().stream()
                .filter(e -> !e.date().isBefore(start) && !e.date().isAfter(end))
                .mapToDouble(Entry::amount)
                .sum();
    }

    public String mostExpensiveItems() {
        return repository.getEntries().stream()
                .sorted(Comparator.comparingDouble(Entry::amount).reversed())
                .map(Entry::productId)
                .distinct()
                .limit(3)
                .sorted() // Сортируем полученные 3 ID по алфавиту
                .collect(Collectors.joining(", "));
    }

    public String statesWithBiggestSales() {
        return repository.getEntries().stream()
                .collect(Collectors.groupingBy(Entry::state, Collectors.summingDouble(Entry::amount)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
    }

    public String findMostProfitableItems() {
        Map<String, Double> salesByProduct = repository.getEntries().stream()
                .collect(Collectors.groupingBy(Entry::productId, Collectors.summingDouble(Entry::amount)));

        return salesByProduct.entrySet().stream()
                .map(entry -> new Pair<>(entry.getKey(), entry.getValue() * accountingService.getProfitMargin(entry.getKey())))
                .sorted(Comparator.comparingDouble(Pair<String, Double>::second).reversed())
                .limit(3)
                .map(Pair::first)
                .collect(Collectors.joining(", "));
    }

    public List<Entry> getAllRecordsPaged(int pageNumber, int pageSize) {
        return repository.getEntries().stream()
                .sorted(Comparator.comparing(Entry::date) // Сортировка по дате (по возрастанию)
                        .thenComparing(Entry::rowNo))    // По номеру строки (по возрастанию)
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .toList();
    }

    public List<String> getCategoryList() {
        // only needed for icd0019app

        return repository.getEntries().stream()
                .map(Entry::category)
                .distinct()
                .sorted()
                .toList();
    }

    public int getRecordCount() {
        // only needed for icd0019app

        return repository.getEntries().size();
    }

}