package cz.petrkobelka.study.moneymagic.parser;

import cz.petrkobelka.study.moneymagic.err.InputArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test cases for ArgumentParser
 *
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
class ArgumentParserTest {

    private static final String DATA_FILE = "src/main/resources/data.txt";

    @Test
    void parse_silent() {

        final String[] arguments = new String[1];
        arguments[0] = "--file=" + DATA_FILE;

        final ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parse(arguments);

        assertNotNull(argumentParser.getInitFile());
        assertEquals(DATA_FILE, argumentParser.getInitFile());
    }

    @Test
    void parse_silent_badArgumentForFile() {
        final String[] arguments = new String[1];
        arguments[0] = "-file=" + DATA_FILE + "";

        final ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.parse(arguments);

        assertNull(argumentParser.getInitFile());
    }

    @Test
    void parse_multipleFiles() {
        final String[] arguments = new String[2];
        arguments[0] = "--file=" + DATA_FILE + "";
        arguments[1] = "--file=" + DATA_FILE + "";

        final ArgumentParser argumentParser = new ArgumentParser();

        Assertions.assertThrows(InputArgumentException.class, () -> {
            argumentParser.parse(arguments);
        });
    }
}
