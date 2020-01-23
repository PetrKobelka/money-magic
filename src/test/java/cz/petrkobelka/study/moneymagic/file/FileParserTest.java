package cz.petrkobelka.study.moneymagic.file;

import cz.petrkobelka.study.moneymagic.err.InitFileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * test cases for FileParse, happy path, common uses, ...
 *
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
class FileParserTest {

    @Test
    void loadData_fileNotExists() {

        final String filePath = "data-test.txtx";
        final FileParser fileParser = new FileParser();

        Assertions.assertThrows(InitFileException.class, () -> {
            fileParser.loadData(filePath);
        });
    }

    @Test
    void loadData() {

        final String filePath = "src/test/resources/data-test.txt";
        final FileParser fileParser = new FileParser();

        final Map<String, Float> loadedValues = fileParser.loadData(filePath);

        Assertions.assertTrue(loadedValues.size() > 0);
        Assertions.assertEquals(3, loadedValues.size());

        Assertions.assertTrue(loadedValues.containsKey("USD"));
        Assertions.assertTrue(loadedValues.containsKey("RMB"));
        Assertions.assertTrue(loadedValues.containsKey("HKD"));

        Assertions.assertEquals(900, loadedValues.get("USD"));
        Assertions.assertEquals(2000, loadedValues.get("RMB"));
        Assertions.assertEquals(300.75f, loadedValues.get("HKD"));
    }

    @Test
    void loadData_invalidData() {

        final String filePath = "src/test/resources/data-test-buggy.txt";
        final FileParser fileParser = new FileParser();

        Assertions.assertThrows(InitFileException.class, () -> {
            fileParser.loadData(filePath);
        });
    }
}
