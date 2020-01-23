package cz.petrkobelka.study.moneymagic.file;

import cz.petrkobelka.study.moneymagic.err.InitFileException;
import cz.petrkobelka.study.moneymagic.parser.CurrencyLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
public class FileParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileParser.class);

    /**
     * try to load data from file
     *
     * @param filePath path to reach file with data to load
     * @return map of currency and its value
     */
    public Map<String, Float> loadData(String filePath) {
        final Path pathToFile = Paths.get(filePath);

        if (!pathToFile.toFile().exists()) {
            LOGGER.error("File does not exists or is unreachable {}", filePath);
            throw new InitFileException("File does not exists or is unreachable: " + filePath);
        } else {
            LOGGER.debug("File exists. Try to load values");
        }

        final Map<String, Float> loadedValues = new HashMap<>();
        final CurrencyLineParser currencyLineParser = new CurrencyLineParser();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {
                final String[] attributes = currencyLineParser.parse(line);

                if (!loadedValues.containsKey(attributes[0])) {
                    loadedValues.put(attributes[0], Float.parseFloat(attributes[1]));
                } else {
                    loadedValues.put(attributes[0], loadedValues.get(attributes[0]) + Float.parseFloat(attributes[1]));
                }

                line = br.readLine();
            }

        } catch (IOException | NumberFormatException e) {
            LOGGER.error(e.getMessage(), e);
            throw new InitFileException(e.getMessage(), e);
        }

        return loadedValues;
    }

}
