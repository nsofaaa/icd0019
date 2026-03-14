package exceptions.numbers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class NumberConverter {

    private List<String> words;

    public NumberConverter(String lang) {

        String path = "src/exceptions/numbers/expected-" + lang + ".txt";

        try {
            words = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);

        } catch (IOException e) {

            if (lang.equals("ru")) {
                throw new MissingLanguageFileException(lang, e);
            }

            if (lang.equals("fr")) {
                throw new BrokenLanguageFileException(lang, e);
            }

            words = List.of();
        }
    }

    public String numberInWords(Integer number) {

        if (number == null) {
            throw new MissingTranslationException("missing translation for null");
        }

        if (number < 0 || number >= words.size()) {
            throw new MissingTranslationException("missing translation" + number);
        }

        String value = words.get(number);

        if (value == null || value.isEmpty()) {
            throw new MissingTranslationException("missing translation" + number);
        }

        return value;
    }
}