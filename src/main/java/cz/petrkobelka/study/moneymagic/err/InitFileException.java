package cz.petrkobelka.study.moneymagic.err;

/**
 * when any big problem with init file appear
 *
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
public class InitFileException extends RuntimeException {

    public InitFileException(String message) {
        super(message);
    }

    public InitFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
