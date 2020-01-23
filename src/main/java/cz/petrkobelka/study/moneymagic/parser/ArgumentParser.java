package cz.petrkobelka.study.moneymagic.parser;

import cz.petrkobelka.study.moneymagic.err.InputArgumentException;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * parse input arguments and allow to read parsed values
 *
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
@Getter
public class ArgumentParser {

    /**
     * if parser find file path in arguments, it will be stored here
     */
    private String initFile = null;

    /**
     * default checker for input arguments
     *
     * @param args array of input arguments
     */
    public void parse(String[] args) {
        if (args != null && args.length > 0) {
            findInitFile(args);
        }
    }

    /**
     * try to parse every argument input if it find file. If so it will store it in initFile property
     *
     * @param arguments array of arguments income when application is initializec
     */
    private void findInitFile(String[] arguments) {

        final Pattern filePattern = Pattern.compile("^--file=(.*)$"); //todo how about path with spaces? on windows ...

        for (int i = 0; i < arguments.length; i++) {
            final Matcher fileMatcher = filePattern.matcher(arguments[i]);
            if (fileMatcher.find()) {
                if (this.initFile == null) {
                    this.initFile = fileMatcher.group(1);
                } else {
                    throw new InputArgumentException("Duplicate file to initialize: " + fileMatcher.group(1));
                }
            }
        }
    }
}
