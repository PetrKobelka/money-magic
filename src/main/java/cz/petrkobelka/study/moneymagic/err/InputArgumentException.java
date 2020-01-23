package cz.petrkobelka.study.moneymagic.err;

/**
 * when input initial argument does not match expected patterns
 *
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
public class InputArgumentException extends RuntimeException {

    public InputArgumentException(String message) {
        super(message);
    }

}
