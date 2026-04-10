package fp.sales;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Repository {

    private static final String FILE_PATH = "src/fp/sales/sales-data.csv";

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public List<Entry> getEntries() {
        // reads lines form the file and creates entry objects for each line.
        try {
            return Files.readAllLines(Paths.get(FILE_PATH)).stream()
                    .skip(1)
                    .map(line -> line.split("\t"))
                    .map(parts -> new Entry(
                            Integer.parseInt(parts[0]),
                            parts[3],
                            LocalDate.parse(parts[1], formatter),
                            parts[2],
                            parts[4],
                            Double.parseDouble(parts[6].replace(",", "."))
                    ))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

