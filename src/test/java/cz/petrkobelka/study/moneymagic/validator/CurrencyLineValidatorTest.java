package cz.petrkobelka.study.moneymagic.validator;

import cz.petrkobelka.study.moneymagic.err.message.CurrencyMessage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test if checker validate all it has
 *
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
class CurrencyLineValidatorTest {

    @Test
    void check() {
        final String[] currency = new String[2];
        currency[0] = "CZK";
        currency[1] = "75";

        final List<String> errors = CurrencyLineValidator.check(currency);
        assertNotNull(errors);
        assertEquals(0L, errors.size());
    }

    @Test
    void check_negativeAmount() {
        final String[] currency = new String[2];
        currency[0] = "CZK";
        currency[1] = "-25";

        final List<String> errors = CurrencyLineValidator.check(currency);
        assertNotNull(errors);
        assertEquals(0L, errors.size());
    }

    @Test
    void check_decimals() {
        final String[] currency = new String[2];
        currency[0] = "CZK";
        currency[1] = "125.7";

        final List<String> errors = CurrencyLineValidator.check(currency);
        assertNotNull(errors);
        assertEquals(0L, errors.size());
    }

    @Test
    void check_invalidCurrency() {
        final String[] currency = new String[2];
        currency[0] = "CK";
        currency[1] = "75";

        final List<String> errors = CurrencyLineValidator.check(currency);
        assertNotNull(errors);
        assertEquals(1L, errors.size());
        assertEquals(String.format(CurrencyMessage.INVALID_CURRENCY_CODE, currency[0]), errors.get(0));
    }

    @Test
    void check_missingCurrency() {
        final String[] currency = new String[2];
        currency[0] = "";
        currency[1] = "75";

        final List<String> errors = CurrencyLineValidator.check(currency);
        assertNotNull(errors);
        assertEquals(1L, errors.size());
        assertEquals(String.format(CurrencyMessage.INVALID_CURRENCY_CODE, currency[0]), errors.get(0));
    }

    @Test
    void check_nullCurrency() {
        final String[] currency = new String[2];
        currency[1] = "75";

        final List<String> errors = CurrencyLineValidator.check(currency);
        assertNotNull(errors);
        assertEquals(1L, errors.size());
        assertEquals(String.format(CurrencyMessage.MISSING_CURRENCY_CODE, currency[0]), errors.get(0));
    }

    @Test
    void check_nullAmount() {
        final String[] currency = new String[2];
        currency[0] = "CZK";

        final List<String> errors = CurrencyLineValidator.check(currency);
        assertNotNull(errors);
        assertEquals(1L, errors.size());
        assertEquals(String.format(CurrencyMessage.MISSING_CURRENCY_AMOUNT, currency[0]), errors.get(0));
    }

    @Test
    void check_invalidAmount() {
        final String[] currency = new String[2];
        currency[0] = "CZK";
        currency[1] = ",087";

        final List<String> errors = CurrencyLineValidator.check(currency);
        assertNotNull(errors);
        assertEquals(1L, errors.size());
        assertEquals(String.format(CurrencyMessage.INVALID_CURRENCY_AMOUNT, currency[1]), errors.get(0));
    }

    @Test
    void check_invalidAmount2() {
        final String[] currency = new String[2];
        currency[0] = "CZK";
        currency[1] = "123 087.87";

        final List<String> errors = CurrencyLineValidator.check(currency);
        assertNotNull(errors);
        assertEquals(1L, errors.size());
        assertEquals(String.format(CurrencyMessage.INVALID_CURRENCY_AMOUNT, currency[1]), errors.get(0));
    }

    @Test
    void check_completeNull() {
        final List<String> errors = CurrencyLineValidator.check(null);
        assertNotNull(errors);
        assertEquals(1L, errors.size());
        assertEquals(CurrencyMessage.INVALID_CURRENCY_DATA, errors.get(0));
    }

}
