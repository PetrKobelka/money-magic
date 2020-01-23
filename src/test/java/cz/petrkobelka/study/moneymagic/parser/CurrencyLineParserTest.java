package cz.petrkobelka.study.moneymagic.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests for verify CurrencyLine parser works properly
 *
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
class CurrencyLineParserTest {

    @Test
    void parse() {

        final String line = "CZK 1550";

        final CurrencyLineParser currencyLineParser = new CurrencyLineParser();

        final String[] result = currencyLineParser.parse(line);

        System.out.println(String.join("-", result));

        assertEquals(2, result.length);
        assertEquals("CZK", result[0]);
        assertEquals("1550", result[1]);

    }
}
