package cz.petrkobelka.study.moneymagic.err.message;

/**
 * system messages to have all on one place to simply manage them
 *
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
public class CurrencyMessage {

    public static final String INVALID_CURRENCY_DATA = "Invalid Currency data. Does not meet basic rules";
    public static final String INVALID_CURRENCY_CODE = "Invalid Currency code %s";
    public static final String MISSING_CURRENCY_CODE = "Invalid Currency is missing";
    public static final String INVALID_CURRENCY_AMOUNT = "Invalid Currency amount %s";
    public static final String MISSING_CURRENCY_AMOUNT = "Currency amount is missing";

    private CurrencyMessage() {
        //only constants no logic inside
    }
}
