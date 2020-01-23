package cz.petrkobelka.study.moneymagic.validator;

import cz.petrkobelka.study.moneymagic.err.message.CurrencyMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * validate if inserted data are correctly written
 * - looking for currency pattern <a href="https://en.wikipedia.org/wiki/ISO_4217">https://en.wikipedia.org/wiki/ISO_4217</a> - must be 3 char
 * - looking for correct order of attributes
 *
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
public class CurrencyLineValidator {

    private static final int CURRENCY_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    private CurrencyLineValidator() {
        //only static methods, there is no need to hold any state
    }

    /**
     * check if all args in currency are OK.
     *
     * @param args parsed currency line into array
     * @return list of founded problems
     */
    public static List<String> check(String[] args) {
        final List<String> errors = new ArrayList<>();

        if (args == null || args.length != 2) {
            errors.add(CurrencyMessage.INVALID_CURRENCY_DATA);
        } else {
            if (args[CURRENCY_INDEX] == null) {
                errors.add(CurrencyMessage.MISSING_CURRENCY_CODE);
            } else {
                final Pattern currencyPattern = Pattern.compile("^[A-Z]{3}$");
                final Matcher currencyMatcher = currencyPattern.matcher(args[CURRENCY_INDEX]);

                if (!currencyMatcher.find()) {
                    errors.add(String.format(CurrencyMessage.INVALID_CURRENCY_CODE, args[CURRENCY_INDEX]));
                }
            }

            if (args[AMOUNT_INDEX] == null) {
                errors.add(CurrencyMessage.MISSING_CURRENCY_AMOUNT);
            } else {
                final Pattern amountPattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
                final Matcher amountMatcher = amountPattern.matcher(args[AMOUNT_INDEX]);

                if (!amountMatcher.find()) {
                    errors.add(String.format(CurrencyMessage.INVALID_CURRENCY_AMOUNT, args[AMOUNT_INDEX]));
                } /*else {
                    if (Float.parseFloat(amountMatcher.group(1)) <= 0) {
                        errors.add(String.format(CurrencyMessage.CURRENCY_AMOUNT_NOT_POSITIVE, args[AMOUNT_INDEX]));
                    }
                }*/


            }
        }

        return errors;
    }
}
