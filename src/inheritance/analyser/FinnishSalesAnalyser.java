package inheritance.analyser;

import java.time.LocalDate;
import java.util.List;

public final class FinnishSalesAnalyser extends AbstractSalesAnalyser {

    public FinnishSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }

    @Override
    protected double getVatRate(LocalDate date) {

        if (date.isBefore(LocalDate.of(2010, 7, 1))) {
            return 0.22;
        }

        if (date.isBefore(LocalDate.of(2013, 1, 1))) {
            return 0.23;
        }

        if (date.isBefore(LocalDate.of(2024, 9, 1))) {
            return 0.24;
        }

        return 0.255;
    }
}