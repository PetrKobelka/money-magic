package cz.petrkobelka.study.moneymagic.parser;

import lombok.NoArgsConstructor;

/**
 * parse one ine of data. It can come from init file or user input
 *
 * @author Petr Kobelka, info@petrkobelka.cz
 * @since 1.0
 */
@NoArgsConstructor
public class CurrencyLineParser {

    public String[] parse(String line) {
        return line.split("\\s");
    }

}
