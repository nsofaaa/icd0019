package inheritance.analyser;

import java.time.LocalDate;
import java.util.*;

public sealed abstract class AbstractSalesAnalyser
        permits EstonianTaxSalesAnalyser, FinnishSalesAnalyser, TaxFreeSalesAnalyser {

    protected List<SalesRecord> records;

    protected AbstractSalesAnalyser(List<SalesRecord> records) {
        this.records = records;
    }

    protected abstract double getVatRate(LocalDate date);

    protected double getNetSales(SalesRecord r) {
        double vat = getVatRate(r.date());
        return r.productPrice() * r.itemsSold() / (1 + vat);
    }

    public Double getTotalSales() {
        double sum = 0;

        for (SalesRecord r : records) {
            sum += getNetSales(r);
        }

        return sum;
    }

    public Double getTotalSalesByProductId(String id) {
        double sum = 0;

        for (SalesRecord r : records) {
            if (r.productId().equals(id)) {
                sum += getNetSales(r);
            }
        }

        return sum;
    }

    public List<String> getTop3PopularItems() {
        Map<String, Integer> map = new HashMap<>();

        for (SalesRecord r : records) {
            String id = r.productId();
            int count = r.itemsSold();

            map.put(id, map.getOrDefault(id, 0) + count);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        List<String> result = new ArrayList<>();

        for (int i = 0; i < 3 && i < list.size(); i++) {
            result.add(list.get(i).getKey());
        }

        return result;
    }

    public Double getLargestTotalSalesAmountForSingleItem() {
        Map<String, Double> map = new HashMap<>();

        for (SalesRecord r : records) {
            String id = r.productId();
            double value = getNetSales(r);

            map.put(id, map.getOrDefault(id, 0.0) + value);
        }

        double max = 0;

        for (double v : map.values()) {
            if (v > max) {
                max = v;
            }
        }

        return max;
    }
}
