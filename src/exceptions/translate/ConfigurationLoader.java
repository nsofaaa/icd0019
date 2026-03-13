package exceptions.translate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class ConfigurationLoader {

    public String readConfiguration(String configFilePath) throws Exception {

        try {
            return String.join("\n", Files.readAllLines(Paths.get(configFilePath)));
        }

        catch (IOException e) {

            if (Files.isDirectory(Paths.get(configFilePath))) {
                throw new InstallationException(e);
            }

            throw new ConfigurationException(e);
        }
    }

}